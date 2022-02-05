package com.example.converterapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ConverterFragment extends Fragment {
    @BindView(R.id.tvName)
    AutoCompleteTextView txtViewName;

    @BindView(R.id.tvName1)
    AutoCompleteTextView txtViewName1;


    @BindView(R.id.tvName2)
    AutoCompleteTextView txtViewName2;

    @BindView(R.id.editTxtInput)
    EditText editTxtInput;

    @BindView(R.id.tvOutput)
    TextView tvOutput;

    @BindView(R.id.btnSubmit)
    Button btnSubmit;

    @BindView(R.id.btnReset)
    Button btnReset;


    private String[] converterOption;
    private String[] currencyList;
    private String[] temperatureList;
    private String[] lengthList;
    private String[] massList;
    private String[] timeList;
    private String[] dataList;



    ArrayAdapter<String> adapterForConverterOption;
    ArrayAdapter<String> adapterForCurrencyList;
    ArrayAdapter<String> adapterForTemperatureList;
    ArrayAdapter<String> adapterForLengthList;
    ArrayAdapter<String> adapterForMassList;
    ArrayAdapter<String> adapterForTimeList;
    ArrayAdapter<String> adapterForDataList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for getContext( fragment

        View view = inflater.inflate(R.layout.fragment_converter, container, false);
        ButterKnife.bind(this,view );
        ui();

        return view;
    }


    private void ui() {

        converterOption = getResources().getStringArray(R.array.ConverterOption);
        currencyList = getResources().getStringArray(R.array.CurrencyList);
        temperatureList = getResources().getStringArray(R.array.TemperatureList);
        lengthList = getResources().getStringArray(R.array.LengthList);
        massList = getResources().getStringArray(R.array.MassList);
        timeList = getResources().getStringArray(R.array.TimeList);
        dataList = getResources().getStringArray(R.array.DataList);

        //Array adapter-------------------------------------------------------------
        adapterForConverterOption = new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, converterOption);
        txtViewName.setAdapter(adapterForConverterOption);

        adapterForCurrencyList = new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, currencyList);
        adapterForTemperatureList = new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, temperatureList);
        adapterForLengthList = new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, lengthList);
        adapterForMassList = new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, massList);
        adapterForTimeList = new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, timeList);
        adapterForDataList = new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, dataList);

        txtViewName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                txtViewName1.setText("");
                txtViewName2.setText("");
                tvOutput.setText("");

                String name = converterOption[i];

                if (name.equalsIgnoreCase("Currency")) {
                    txtViewName1.setAdapter(adapterForCurrencyList);
                    txtViewName2.setAdapter(adapterForCurrencyList);

                } else if (name.equalsIgnoreCase("Temperature")) {
                    txtViewName1.setAdapter(adapterForTemperatureList);
                    txtViewName2.setAdapter(adapterForTemperatureList);

                } else if (name.equalsIgnoreCase("Length")) {
                    txtViewName1.setAdapter(adapterForLengthList);
                    txtViewName2.setAdapter(adapterForLengthList);

                } else if (name.equalsIgnoreCase("Mass")) {
                    txtViewName1.setAdapter(adapterForMassList);
                    txtViewName2.setAdapter(adapterForMassList);

                } else if (name.equalsIgnoreCase("Time")) {
                    txtViewName1.setAdapter(adapterForTimeList);
                    txtViewName2.setAdapter(adapterForTimeList);

                } else if (name.equalsIgnoreCase("Data")) {
                    txtViewName1.setAdapter(adapterForDataList);
                    txtViewName2.setAdapter(adapterForDataList);
                }
            }
        });

        txtViewName1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                tvOutput.setText("");
            }
        });

        txtViewName2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                tvOutput.setText("");
            }
        });




        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tvNameString = txtViewName.getText().toString().trim();
                String tvNameString1 = txtViewName1.getText().toString().trim();
                String tvNameString2 = txtViewName2.getText().toString().trim();
                String etInputString = editTxtInput.getText().toString().trim();


                if (TextUtils.isEmpty(tvNameString) || TextUtils.isEmpty(tvNameString1) || TextUtils.isEmpty(tvNameString2) || TextUtils.isEmpty(etInputString)) {
                    Toast.makeText(getContext(), "Please fill up all the field properly", Toast.LENGTH_SHORT).show();
                } else {

                    try {
                        double etInput = Double.parseDouble(etInputString);

                        if (tvNameString.equalsIgnoreCase("Currency")) {
                            currencyConverter(tvNameString1, tvNameString2, etInput);

                        } else if (tvNameString.equalsIgnoreCase("Temperature")) {
                            temperatureConverter(tvNameString1, tvNameString2, etInput);

                        } else if (tvNameString.equalsIgnoreCase("Length")) {
                            lengthConverter(tvNameString1, tvNameString2, etInput);

                        } else if (tvNameString.equalsIgnoreCase("Mass")) {
                            massConverter(tvNameString1, tvNameString2, etInput);

                        } else if (tvNameString.equalsIgnoreCase("Time")) {
                            timeConverter(tvNameString1, tvNameString2, etInput);

                        } else if (tvNameString.equalsIgnoreCase("Data")) {
                            dataConverter(tvNameString1, tvNameString2, etInput);
                        }
                    } catch (NumberFormatException e) {
                        Toast.makeText(getContext(), "Please give a valid number", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTxtInput.setText("");
                tvOutput.setText("");
            }
        });


    }


    private void currencyConverter(@NonNull String tvNameString1, String tvNameString2, Double etInput) {
        if ((tvNameString1.equalsIgnoreCase("Taka")) && (tvNameString2.equalsIgnoreCase("Rupee"))) {
            tvOutput.setText(String.valueOf(convertTakaToRupee(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Rupee")) && (tvNameString2.equalsIgnoreCase("Taka"))) {
            tvOutput.setText(String.valueOf(convertRupeeToTaka(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Taka")) && (tvNameString2.equalsIgnoreCase("Doller"))) {
            tvOutput.setText(String.valueOf(convertTakaToDoller(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Doller")) && (tvNameString2.equalsIgnoreCase("Taka"))) {
            tvOutput.setText(String.valueOf(convertDollerToTaka(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Doller")) && (tvNameString2.equalsIgnoreCase("Rupee"))) {
            tvOutput.setText(String.valueOf(convertDollerToRupee(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Rupee")) && (tvNameString2.equalsIgnoreCase("Doller"))) {
            tvOutput.setText(String.valueOf(convertRupeeToDoller(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Rupee")) && (tvNameString2.equalsIgnoreCase("Rupee"))) {
            tvOutput.setText(String.valueOf(convertRupeeToRupee(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Doller")) && (tvNameString2.equalsIgnoreCase("Doller"))) {
            tvOutput.setText(String.valueOf(convertDollerToDoller(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Taka")) && (tvNameString2.equalsIgnoreCase("Taka"))) {
            tvOutput.setText(String.valueOf(convertTakaToTaka(etInput)));

        }


    }


    private void temperatureConverter(@NonNull String tvNameString1, String tvNameString2, Double etInput) {
        if ((tvNameString1.equalsIgnoreCase("Celsius")) && (tvNameString2.equalsIgnoreCase("Fahrenheit"))) {
            tvOutput.setText(String.valueOf(convertCelsiusToFahrenheit(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Fahrenheit")) && (tvNameString2.equalsIgnoreCase("Celsius"))) {
            tvOutput.setText(String.valueOf(convertFahrenheitToCelsius(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Celsius")) && (tvNameString2.equalsIgnoreCase("Celsius"))) {
            tvOutput.setText(String.valueOf(convertCelsiusToCelsius(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Fahrenheit")) && (tvNameString2.equalsIgnoreCase("Fahrenheit"))) {
            tvOutput.setText(String.valueOf(convertFahrenheitToFahrenheit(etInput)));

        }
    }

    private void lengthConverter(@NonNull String tvNameString1, String tvNameString2, Double etInput) {

        if ((tvNameString1.equalsIgnoreCase("Inches")) && (tvNameString2.equalsIgnoreCase("Inches"))) {
            tvOutput.setText(String.valueOf(convertInchesToInches(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Inches")) && (tvNameString2.equalsIgnoreCase("Feet"))) {
            tvOutput.setText(String.valueOf(convertInchesToFeet(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Inches")) && (tvNameString2.equalsIgnoreCase("Centimeter"))) {
            tvOutput.setText(String.valueOf(convertInchesToCentimeter(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Inches")) && (tvNameString2.equalsIgnoreCase("Meter"))) {
            tvOutput.setText(String.valueOf(convertInchesToMeter(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Inches")) && (tvNameString2.equalsIgnoreCase("Kilometer"))) {
            tvOutput.setText(String.valueOf(convertInchesToKilometer(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Inches")) && (tvNameString2.equalsIgnoreCase("Millimeter"))) {
            tvOutput.setText(String.valueOf(convertInchesToMillimeter(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Feet")) && (tvNameString2.equalsIgnoreCase("Feet"))) {
            tvOutput.setText(String.valueOf(convertFeetToFeet(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Feet")) && (tvNameString2.equalsIgnoreCase("Inches"))) {
            tvOutput.setText(String.valueOf(convertFeetToInches(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Feet")) && (tvNameString2.equalsIgnoreCase("Centimeter"))) {
            tvOutput.setText(String.valueOf(convertFeetToCentimeter(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Feet")) && (tvNameString2.equalsIgnoreCase("Meter"))) {
            tvOutput.setText(String.valueOf(convertFeetToMeter(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Feet")) && (tvNameString2.equalsIgnoreCase("Kilometer"))) {
            tvOutput.setText(String.valueOf(convertFeetToKilometer(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Feet")) && (tvNameString2.equalsIgnoreCase("Millimeter"))) {
            tvOutput.setText(String.valueOf(convertFeetToMillimeter(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Centimeter")) && (tvNameString2.equalsIgnoreCase("Centimeter"))) {
            tvOutput.setText(String.valueOf(convertCentimeterToCentimeter(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Centimeter")) && (tvNameString2.equalsIgnoreCase("Inches"))) {
            tvOutput.setText(String.valueOf(convertCentimeterToInches(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Centimeter")) && (tvNameString2.equalsIgnoreCase("Feet"))) {
            tvOutput.setText(String.valueOf(convertCentimeterToFeet(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Centimeter")) && (tvNameString2.equalsIgnoreCase("Meter"))) {
            tvOutput.setText(String.valueOf(convertCentimeterToMeter(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Centimeter")) && (tvNameString2.equalsIgnoreCase("Kilometer"))) {
            tvOutput.setText(String.valueOf(convertCentimeterToKilometer(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Centimeter")) && (tvNameString2.equalsIgnoreCase("Millimeter"))) {
            tvOutput.setText(String.valueOf(convertCentimeterToMillimeter(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Meter")) && (tvNameString2.equalsIgnoreCase("Meter"))) {
            tvOutput.setText(String.valueOf(convertMeterToMeter(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Meter")) && (tvNameString2.equalsIgnoreCase("Inches"))) {
            tvOutput.setText(String.valueOf(convertMeterToInches(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Meter")) && (tvNameString2.equalsIgnoreCase("Feet"))) {
            tvOutput.setText(String.valueOf(convertMeterToFeet(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Meter")) && (tvNameString2.equalsIgnoreCase("Centimeter"))) {
            tvOutput.setText(String.valueOf(convertMeterToCentimeter(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Meter")) && (tvNameString2.equalsIgnoreCase("Kilometer"))) {
            tvOutput.setText(String.valueOf(convertMeterToKilometer(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Meter")) && (tvNameString2.equalsIgnoreCase("Millimeter"))) {
            tvOutput.setText(String.valueOf(convertMeterToMillimeter(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Kilometer")) && (tvNameString2.equalsIgnoreCase("Kilometer"))) {
            tvOutput.setText(String.valueOf(convertKilometerToKiloMeter(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Kilometer")) && (tvNameString2.equalsIgnoreCase("Inches"))) {
            tvOutput.setText(String.valueOf(convertKilometerToInches(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Kilometer")) && (tvNameString2.equalsIgnoreCase("Feet"))) {
            tvOutput.setText(String.valueOf(convertKilometerToFeet(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Kilometer")) && (tvNameString2.equalsIgnoreCase("Centimeter"))) {
            tvOutput.setText(String.valueOf(convertKilometerToCentimeter(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Kilometer")) && (tvNameString2.equalsIgnoreCase("Meter"))) {
            tvOutput.setText(String.valueOf(convertKilometerToMeter(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Kilometer")) && (tvNameString2.equalsIgnoreCase("Millimeter"))) {
            tvOutput.setText(String.valueOf(convertKilometerToMillimeter(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Millimeter")) && (tvNameString2.equalsIgnoreCase("Millimeter"))) {
            tvOutput.setText(String.valueOf(convertMillimeterToMillimeter(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Millimeter")) && (tvNameString2.equalsIgnoreCase("Inches"))) {
            tvOutput.setText(String.valueOf(convertMillimeterToInches(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Millimeter")) && (tvNameString2.equalsIgnoreCase("Feet"))) {
            tvOutput.setText(String.valueOf(convertMillimeterToFeet(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Millimeter")) && (tvNameString2.equalsIgnoreCase("Centimeter"))) {
            tvOutput.setText(String.valueOf(convertMillimeterToCentimeter(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Millimeter")) && (tvNameString2.equalsIgnoreCase("Meter"))) {
            tvOutput.setText(String.valueOf(convertMillimeterToMeter(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Millimeter")) && (tvNameString2.equalsIgnoreCase("Kilometer"))) {
            tvOutput.setText(String.valueOf(convertMillimeterToKilometer(etInput)));

        }
    }

    private void massConverter(@NonNull String tvNameString1, String tvNameString2, Double etInput) {

        if ((tvNameString1.equalsIgnoreCase("Milligram")) && (tvNameString2.equalsIgnoreCase("Milligram"))) {
            tvOutput.setText(String.valueOf(convertMilligramToMilligram(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Milligram")) && (tvNameString2.equalsIgnoreCase("Centigram"))) {
            tvOutput.setText(String.valueOf(convertMilligramToCentigram(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Milligram")) && (tvNameString2.equalsIgnoreCase("Gram"))) {
            tvOutput.setText(String.valueOf(convertMilligramToGram(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Milligram")) && (tvNameString2.equalsIgnoreCase("Kilogram"))) {
            tvOutput.setText(String.valueOf(convertMilligramToKilogram(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Milligram")) && (tvNameString2.equalsIgnoreCase("Tonne"))) {
            tvOutput.setText(String.valueOf(convertMilligramToTonne(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Centigram")) && (tvNameString2.equalsIgnoreCase("Centigram"))) {
            tvOutput.setText(String.valueOf(convertCentigramToCentigram(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Centigram")) && (tvNameString2.equalsIgnoreCase("Milligram"))) {
            tvOutput.setText(String.valueOf(convertCentigramToMilligram(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Centigram")) && (tvNameString2.equalsIgnoreCase("Gram"))) {
            tvOutput.setText(String.valueOf(convertCentigramToGram(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Centigram")) && (tvNameString2.equalsIgnoreCase("Kilogram"))) {
            tvOutput.setText(String.valueOf(convertCentigramToKilogram(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Centigram")) && (tvNameString2.equalsIgnoreCase("Tonne"))) {
            tvOutput.setText(String.valueOf(convertCentigramToTonne(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Gram")) && (tvNameString2.equalsIgnoreCase("Gram"))) {
            tvOutput.setText(String.valueOf(convertGramToGram(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Gram")) && (tvNameString2.equalsIgnoreCase("Milligram"))) {
            tvOutput.setText(String.valueOf(convertGramToMilligram(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Gram")) && (tvNameString2.equalsIgnoreCase("Centigram"))) {
            tvOutput.setText(String.valueOf(convertGramToCentigram(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Gram")) && (tvNameString2.equalsIgnoreCase("Kilogram"))) {
            tvOutput.setText(String.valueOf(convertGramToKilogram(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Gram")) && (tvNameString2.equalsIgnoreCase("Tonne"))) {
            tvOutput.setText(String.valueOf(convertGramToTonne(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Kilogram")) && (tvNameString2.equalsIgnoreCase("Kilogram"))) {
            tvOutput.setText(String.valueOf(convertKilogramToKilogram(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Kilogram")) && (tvNameString2.equalsIgnoreCase("Milligram"))) {
            tvOutput.setText(String.valueOf(convertKilogramToMilligram(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Kilogram")) && (tvNameString2.equalsIgnoreCase("Centigram"))) {
            tvOutput.setText(String.valueOf(convertKilogramToCentigram(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Kilogram")) && (tvNameString2.equalsIgnoreCase("Gram"))) {
            tvOutput.setText(String.valueOf(convertKilogramToGram(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Kilogram")) && (tvNameString2.equalsIgnoreCase("Tonne"))) {
            tvOutput.setText(String.valueOf(convertKilogramToTonne(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Tonne")) && (tvNameString2.equalsIgnoreCase("Tonne"))) {
            tvOutput.setText(String.valueOf(convertTonneToTonne(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Tonne")) && (tvNameString2.equalsIgnoreCase("Milligram"))) {
            tvOutput.setText(String.valueOf(convertTonneToMilligram(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Tonne")) && (tvNameString2.equalsIgnoreCase("Centigram"))) {
            tvOutput.setText(String.valueOf(convertTonneToCentigram(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Tonne")) && (tvNameString2.equalsIgnoreCase("Gram"))) {
            tvOutput.setText(String.valueOf(convertTonneToGram(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Tonne")) && (tvNameString2.equalsIgnoreCase("Kilogram"))) {
            tvOutput.setText(String.valueOf(convertTonneToKilogram(etInput)));

        }
    }

    private void timeConverter(@NonNull String tvNameString1, String tvNameString2, Double etInput) {

        if ((tvNameString1.equalsIgnoreCase("Seconds")) && (tvNameString2.equalsIgnoreCase("Seconds"))) {
            tvOutput.setText(String.valueOf(convertSecondsToSeconds(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Seconds")) && (tvNameString2.equalsIgnoreCase("Minute"))) {
            tvOutput.setText(String.valueOf(convertSecondsToMinute(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Seconds")) && (tvNameString2.equalsIgnoreCase("Hour"))) {
            tvOutput.setText(String.valueOf(convertSecondsToHour(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Minute")) && (tvNameString2.equalsIgnoreCase("Minute"))) {
            tvOutput.setText(String.valueOf(convertMinuteToMinute(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Minute")) && (tvNameString2.equalsIgnoreCase("Seconds"))) {
            tvOutput.setText(String.valueOf(convertMinuteToSeconds(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Minute")) && (tvNameString2.equalsIgnoreCase("Hour"))) {
            tvOutput.setText(String.valueOf(convertMinuteToHour(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Hour")) && (tvNameString2.equalsIgnoreCase("Hour"))) {
            tvOutput.setText(String.valueOf(convertHourToHour(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Hour")) && (tvNameString2.equalsIgnoreCase("Seconds"))) {
            tvOutput.setText(String.valueOf(convertHourToSeconds(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Hour")) && (tvNameString2.equalsIgnoreCase("Minute"))) {
            tvOutput.setText(String.valueOf(convertHourToMinute(etInput)));

        }

    }

    private void dataConverter(@NonNull String tvNameString1, String tvNameString2, Double etInput) {

        if ((tvNameString1.equalsIgnoreCase("Bit")) && (tvNameString2.equalsIgnoreCase("Bit"))) {
            tvOutput.setText(String.valueOf(convertBitToBit(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Bit")) && (tvNameString2.equalsIgnoreCase("Byte"))) {
            tvOutput.setText(String.valueOf(convertBitToByte(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Bit")) && (tvNameString2.equalsIgnoreCase("KB"))) {
            tvOutput.setText(String.valueOf(convertBitToKiloByte(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Bit")) && (tvNameString2.equalsIgnoreCase("MB"))) {
            tvOutput.setText(String.valueOf(convertBitToMegaByte(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Bit")) && (tvNameString2.equalsIgnoreCase("GB"))) {
            tvOutput.setText(String.valueOf(convertBitToGigaByte(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Bit")) && (tvNameString2.equalsIgnoreCase("TB"))) {
            tvOutput.setText(String.valueOf(convertBitToTeraByte(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Byte")) && (tvNameString2.equalsIgnoreCase("Byte"))) {
            tvOutput.setText(String.valueOf(convertByteToByte(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Byte")) && (tvNameString2.equalsIgnoreCase("Bit"))) {
            tvOutput.setText(String.valueOf(convertByteToBit(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Byte")) && (tvNameString2.equalsIgnoreCase("KB"))) {
            tvOutput.setText(String.valueOf(convertByteToKiloByte(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Byte")) && (tvNameString2.equalsIgnoreCase("MB"))) {
            tvOutput.setText(String.valueOf(convertByteToMegaByte(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Byte")) && (tvNameString2.equalsIgnoreCase("GB"))) {
            tvOutput.setText(String.valueOf(convertByteToGigaByte(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("Byte")) && (tvNameString2.equalsIgnoreCase("TB"))) {
            tvOutput.setText(String.valueOf(convertByteToTeraByte(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("KB")) && (tvNameString2.equalsIgnoreCase("KB"))) {
            tvOutput.setText(String.valueOf(convertKiloByteToKiloByte(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("KB")) && (tvNameString2.equalsIgnoreCase("Bit"))) {
            tvOutput.setText(String.valueOf(convertKiloByteToBit(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("KB")) && (tvNameString2.equalsIgnoreCase("Byte"))) {
            tvOutput.setText(String.valueOf(convertKiloByteToByte(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("KB")) && (tvNameString2.equalsIgnoreCase("MB"))) {
            tvOutput.setText(String.valueOf(convertKiloByteToMegaByte(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("KB")) && (tvNameString2.equalsIgnoreCase("GB"))) {
            tvOutput.setText(String.valueOf(convertKiloByteToGigaByte(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("KB")) && (tvNameString2.equalsIgnoreCase("TB"))) {
            tvOutput.setText(String.valueOf(convertKiloByteToTeraByte(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("MB")) && (tvNameString2.equalsIgnoreCase("MB"))) {
            tvOutput.setText(String.valueOf(convertMegaByteToMegaByte(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("MB")) && (tvNameString2.equalsIgnoreCase("Bit"))) {
            tvOutput.setText(String.valueOf(convertMegaByteToBit(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("MB")) && (tvNameString2.equalsIgnoreCase("Byte"))) {
            tvOutput.setText(String.valueOf(convertMegaByteToByte(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("MB")) && (tvNameString2.equalsIgnoreCase("KB"))) {
            tvOutput.setText(String.valueOf(convertMegaByteToKiloByte(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("MB")) && (tvNameString2.equalsIgnoreCase("GB"))) {
            tvOutput.setText(String.valueOf(convertMegaByteToGigaByte(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("MB")) && (tvNameString2.equalsIgnoreCase("TB"))) {
            tvOutput.setText(String.valueOf(convertMegaByteToTeraByte(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("GB")) && (tvNameString2.equalsIgnoreCase("GB"))) {
            tvOutput.setText(String.valueOf(convertGigaByteToGigaByte(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("GB")) && (tvNameString2.equalsIgnoreCase("Bit"))) {
            tvOutput.setText(String.valueOf(convertGigaByteToBit(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("GB")) && (tvNameString2.equalsIgnoreCase("Byte"))) {
            tvOutput.setText(String.valueOf(convertGigaByteToByte(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("GB")) && (tvNameString2.equalsIgnoreCase("KB"))) {
            tvOutput.setText(String.valueOf(convertGigaByteToKiloByte(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("GB")) && (tvNameString2.equalsIgnoreCase("MB"))) {
            tvOutput.setText(String.valueOf(convertGigaByteToMegaByte(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("GB")) && (tvNameString2.equalsIgnoreCase("TB"))) {
            tvOutput.setText(String.valueOf(convertGigaByteToTeraByte(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("TB")) && (tvNameString2.equalsIgnoreCase("TB"))) {
            tvOutput.setText(String.valueOf(convertTeraByteToTeraByte(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("TB")) && (tvNameString2.equalsIgnoreCase("Bit"))) {
            tvOutput.setText(String.valueOf(convertTeraByteToBit(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("TB")) && (tvNameString2.equalsIgnoreCase("Byte"))) {
            tvOutput.setText(String.valueOf(convertTeraByteToByte(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("TB")) && (tvNameString2.equalsIgnoreCase("KB"))) {
            tvOutput.setText(String.valueOf(convertTeraByteToKiloByte(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("TB")) && (tvNameString2.equalsIgnoreCase("MB"))) {
            tvOutput.setText(String.valueOf(convertTeraByteToMegaByte(etInput)));

        } else if ((tvNameString1.equalsIgnoreCase("TB")) && (tvNameString2.equalsIgnoreCase("GB"))) {
            tvOutput.setText(String.valueOf(convertTeraByteToGigaByte(etInput)));

        }
    }


    private double convertTakaToRupee(double d) {
        return (d * 0.86);
    }

    private double convertRupeeToTaka(double d) {
        return (d * 1.17);
    }

    private double convertTakaToDoller(double d) {
        return (d / 80);
    }

    private double convertDollerToTaka(double d) {
        return (d * 80);
    }

    private double convertDollerToRupee(double d) {
        return (d * 74);
    }

    private double convertRupeeToDoller(double d) {
        return (d / 74);
    }

    private double convertTakaToTaka(double d) {
        return d;
    }

    private double convertRupeeToRupee(double d) {
        return d;
    }

    private double convertDollerToDoller(double d) {
        return d;
    }

    //Temperature conversion---------------------------------------------------------------------------
    private double convertCelsiusToCelsius(double celsius) {
        return celsius;
    }

    private double convertFahrenheitToFahrenheit(double fahrenheit) {
        return fahrenheit;
    }

    private double convertCelsiusToFahrenheit(double celsius) {
        return ((celsius * 9) / 5) + 32;
    }

    private double convertFahrenheitToCelsius(double fahrenheit) {
        return ((fahrenheit - 32) * 5) / 9;
    }

    //Length conversion start==============================================================================
    //Conversion Inches to others-----------------------------------------------------------------
    private double convertInchesToInches(double d) {
        return d;
    }

    private double convertInchesToFeet(double d) {
        return (d / 12);
    }

    private double convertInchesToCentimeter(double d) {
        return (d * 2.54);
    }

    private double convertInchesToMeter(double d) {
        return (d / 39.37);
    }

    private double convertInchesToKilometer(double d) {
        return (d / (1000 * 39.37));
    }

    private double convertInchesToMillimeter(double d) {
        return (d * 25.40);
    }

    //Conversion Feet to others-----------------------------------------------------------------
    private double convertFeetToFeet(double d) {
        return d;
    }

    private double convertFeetToInches(double d) {
        return (d * 12);
    }

    private double convertFeetToCentimeter(double d) {
        return (d * 30.48);
    }

    private double convertFeetToMeter(double d) {
        return (d / 3.28);
    }

    private double convertFeetToKilometer(double d) {
        return (d / (1000 * 3.28));
    }

    private double convertFeetToMillimeter(double d) {
        return (d * 304.87);
    }

    //Conversion Centimeter to others-----------------------------------------------------------------
    private double convertCentimeterToCentimeter(double d) {
        return d;
    }

    private double convertCentimeterToInches(double d) {
        return (d / 2.54);
    }

    private double convertCentimeterToFeet(double d) {
        return (d / 30.48);
    }

    private double convertCentimeterToMeter(double d) {
        return (d / 100);
    }

    private double convertCentimeterToKilometer(double d) {
        return (d / 100000);
    }

    private double convertCentimeterToMillimeter(double d) {
        return (d * 10);
    }


    //Conversion Meter to others-----------------------------------------------------------------
    private double convertMeterToMeter(double d) {
        return d;
    }

    private double convertMeterToInches(double d) {
        return (d * 39.37);
    }

    private double convertMeterToFeet(double d) {
        return (d * 3.28);
    }

    private double convertMeterToCentimeter(double d) {
        return (d * 100);
    }

    private double convertMeterToKilometer(double d) {
        return (d / 1000);
    }

    private double convertMeterToMillimeter(double d) {
        return (d * 1000);
    }


    //Conversion Kilometer to others-----------------------------------------------------------------
    private double convertKilometerToKiloMeter(double d) {
        return d;
    }

    private double convertKilometerToInches(double d) {
        return (d * (1000 * 39.37));
    }

    private double convertKilometerToFeet(double d) {
        return (d * (1000 * 3.28));
    }

    private double convertKilometerToCentimeter(double d) {
        return (d * (1000 * 100));
    }

    private double convertKilometerToMeter(double d) {
        return (d * 1000);
    }

    private double convertKilometerToMillimeter(double d) {
        return (d * (1000 * 1000));
    }


    //Conversion Millimeter to others-----------------------------------------------------------------
    private double convertMillimeterToMillimeter(double d) {
        return d;
    }

    private double convertMillimeterToInches(double d) {
        return (d / 25.40);
    }

    private double convertMillimeterToFeet(double d) {
        return (d / 304.87);
    }

    private double convertMillimeterToCentimeter(double d) {
        return (d / 10);
    }

    private double convertMillimeterToMeter(double d) {
        return (d / 1000);
    }

    private double convertMillimeterToKilometer(double d) {
        return (d / (1000 * 1000));
    }

    //Length conversion end=======================================================================

    //Mass conversion start========================================================================
    //Conversion milligram to others--------------------------
    private double convertMilligramToMilligram(double d) {
        return d;
    }

    private double convertMilligramToCentigram(double d) {
        return (d / 10);
    }

    private double convertMilligramToGram(double d) {
        return (d / 1000);
    }

    private double convertMilligramToKilogram(double d) {
        return (d / 1000000);
    }

    private double convertMilligramToTonne(double d) {
        return (d / 1000000000);
    }

    //Conversion centigram to others-------------------------
    private double convertCentigramToCentigram(double d) {
        return d;
    }

    private double convertCentigramToMilligram(double d) {
        return (d * 10);
    }

    private double convertCentigramToGram(double d) {
        return (d / 100);
    }

    private double convertCentigramToKilogram(double d) {
        return (d / 100000);
    }

    private double convertCentigramToTonne(double d) {
        return (d / 100000000);
    }

    //Conversion gram to others-----------------------------
    private double convertGramToGram(double d) {
        return d;
    }

    private double convertGramToMilligram(double d) {
        return (d * 1000);
    }

    private double convertGramToCentigram(double d) {
        return (d * 100);
    }

    private double convertGramToKilogram(double d) {
        return (d / 1000);
    }

    private double convertGramToTonne(double d) {
        return (d / 1000000);
    }

    //Conversion kilogram to others-------------------------
    private double convertKilogramToKilogram(double d) {
        return d;
    }

    private double convertKilogramToMilligram(double d) {
        return (d * 1000000);
    }

    private double convertKilogramToCentigram(double d) {
        return (d * 100000);
    }

    private double convertKilogramToGram(double d) {
        return (d * 1000);
    }

    private double convertKilogramToTonne(double d) {
        return (d / 1000);
    }


    //Conversion tonne to others-------------------------
    private double convertTonneToTonne(double d) {
        return d;
    }

    private double convertTonneToMilligram(double d) {
        return (d * 1000000000);
    }

    private double convertTonneToCentigram(double d) {
        return (d * 100000000);
    }

    private double convertTonneToGram(double d) {
        return (d * 1000000);
    }

    private double convertTonneToKilogram(double d) {
        return (d * 1000);
    }

    //Time conversion start================================================================
    //Conversion seconds to others-----------------------------------

    private double convertSecondsToSeconds(double d) {
        return d;
    }

    private double convertSecondsToMinute(double d) {
        return (d / 60);
    }

    private double convertSecondsToHour(double d) {
        return (d / (60 * 60));
    }

    //Conversion minute to others-----------------------------------
    private double convertMinuteToMinute(double d) {
        return d;
    }

    private double convertMinuteToSeconds(double d) {
        return (d * 60);
    }

    private double convertMinuteToHour(double d) {
        return (d / 60);
    }

    //Conversion hour to others-----------------------------------
    private double convertHourToHour(double d) {
        return d;
    }

    private double convertHourToSeconds(double d) {
        return (d * (60 * 60));
    }

    private double convertHourToMinute(double d) {
        return (d * 60);
    }

    //Time conversion end================================================================


    //Data conversion start==============================================================
    //Conversion bit to others------------------------------------
    private double convertBitToBit(double d) {
        return d;
    }

    private double convertBitToByte(double d) {
        return (d / 8);
    }

    private double convertBitToKiloByte(double d) {
        return (d / 8000);
    }

    private double convertBitToMegaByte(double d) {
        return (d / 8000000);
    }

    private double convertBitToGigaByte(double d) {
        return (d / (1000000000 * 8));
    }

    private double convertBitToTeraByte(double d) {
        return (d / ((1000000 * 1000) * 8000));
    }

    //Conversion byte to others------------------------------------------
    private double convertByteToByte(double d) {
        return d;
    }

    private double convertByteToBit(double d) {
        return (d * 8);
    }

    private double convertByteToKiloByte(double d) {
        return (d / 1000);
    }

    private double convertByteToMegaByte(double d) {
        return (d / 1000000);
    }

    private double convertByteToGigaByte(double d) {
        return (d / 1000000000);
    }

    private double convertByteToTeraByte(double d) {
        return (d / (1000 * 1000 * 1000 * 1000));
    }

    //Conversion kilobyte to others-------------------------------------------
    private double convertKiloByteToKiloByte(double d) {
        return d;
    }

    private double convertKiloByteToBit(double d) {
        return (d / 8000);
    }

    private double convertKiloByteToByte(double d) {
        return (d / 1000);
    }

    private double convertKiloByteToMegaByte(double d) {
        return (d / 1000);
    }

    private double convertKiloByteToGigaByte(double d) {
        return (d / 1000000);
    }

    private double convertKiloByteToTeraByte(double d) {
        return (d / (1000000 * 1000));
    }

    //Conversion megabyte to others-------------------------------------------
    private double convertMegaByteToMegaByte(double d) {
        return d;
    }

    private double convertMegaByteToBit(double d) {
        return (d * 8000000);
    }

    private double convertMegaByteToByte(double d) {
        return (d * 1000000);
    }

    private double convertMegaByteToKiloByte(double d) {
        return (d * 1000);
    }

    private double convertMegaByteToGigaByte(double d) {
        return (d / 1000);
    }

    private double convertMegaByteToTeraByte(double d) {
        return (d / 1000000);
    }

    //Conversion gigabyte to others-------------------------------------------
    private double convertGigaByteToGigaByte(double d) {
        return d;
    }

    private double convertGigaByteToBit(double d) {
        return (d * (1000000000 * 8));
    }

    private double convertGigaByteToByte(double d) {
        return (d * 1000000000);
    }

    private double convertGigaByteToKiloByte(double d) {
        return (d * 1000000);
    }

    private double convertGigaByteToMegaByte(double d) {
        return (d * 1000);
    }

    private double convertGigaByteToTeraByte(double d) {
        return (d / 1000);
    }

    //Conversion terabyte to others-------------------------------------------
    private double convertTeraByteToTeraByte(double d) {
        return d;
    }

    private double convertTeraByteToBit(double d) {
        return (d * (1000 * 1000 * 1000 * 1000 * 8));
    }

    private double convertTeraByteToByte(double d) {
        return (d * (1000 * 1000 * 1000 * 1000));
    }

    private double convertTeraByteToKiloByte(double d) {
        return (d * (1000000 * 1000));
    }

    private double convertTeraByteToMegaByte(double d) {
        return (d * 1000000);
    }

    private double convertTeraByteToGigaByte(double d) {
        return (d * 1000);
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}