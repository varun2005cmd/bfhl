package com.example.bfhl.service;

import com.example.bfhl.dto.BfhlRequestDto;
import com.example.bfhl.dto.BfhlResponseDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BfhlServiceImpl implements BfhlService {

    private static final String USER_ID = "varun_21092005";
    private static final String EMAIL = "varun.btech2023@sitpune.edu.in";
    private static final String ROLL_NUMBER = "23070123149";

    @Override
    public BfhlResponseDto process(BfhlRequestDto request) {
        List<String> data = request.getData();

        List<String> oddNumbers = new ArrayList<>();
        List<String> evenNumbers = new ArrayList<>();
        List<String> alphabets = new ArrayList<>();
        List<String> specialCharacters = new ArrayList<>();
        long numericSum = 0;

        for (String item : data) {
            if (isNumber(item)) {
                long val = Long.parseLong(item);
                numericSum += val;
                if (val % 2 == 0) {
                    evenNumbers.add(item);
                } else {
                    oddNumbers.add(item);
                }
            } else if (isAlphabet(item)) {
                alphabets.add(item.toUpperCase());
            } else {
                specialCharacters.add(item);
            }
        }

        String concatString = buildConcatString(alphabets);

        return BfhlResponseDto.builder()
                .isSuccess(true)
                .userId(USER_ID)
                .email(EMAIL)
                .rollNumber(ROLL_NUMBER)
                .oddNumbers(oddNumbers)
                .evenNumbers(evenNumbers)
                .alphabets(alphabets)
                .specialCharacters(specialCharacters)
                .sum(String.valueOf(numericSum))
                .concatString(concatString)
                .build();
    }

    private boolean isNumber(String s) {
        if (s == null || s.isEmpty()) return false;
        try {
            Long.parseLong(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isAlphabet(String s) {
        if (s == null || s.isEmpty()) return false;
        for (char c : s.toCharArray()) {
            if (!Character.isLetter(c)) return false;
        }
        return true;
    }

    private String buildConcatString(List<String> alphabetList) {
        StringBuilder combined = new StringBuilder();
        for (String word : alphabetList) {
            combined.append(word);
        }

        String reversed = combined.reverse().toString();

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < reversed.length(); i++) {
            char c = reversed.charAt(i);
            if (i % 2 == 0) {
                result.append(Character.toUpperCase(c));
            } else {
                result.append(Character.toLowerCase(c));
            }
        }

        return result.toString();
    }
}
