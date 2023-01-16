package com.learning.hitungnilaiakhir;

public class Nilai {
    public float getNAkhir(float nA, float nB, float nC) {
        return nA + nB + nC;
    }

    public String getNHuruf(float nAkhir) {
        if (nAkhir >= 85) {
            return "A";
        } else if (nAkhir >= 80) {
            return "AB";
        } else if (nAkhir >= 70) {
            return "B";
        } else if (nAkhir >= 65) {
            return "BC";
        } else if (nAkhir >= 60) {
            return "C";
        } else if (nAkhir >= 40) {
            return "D";
        } else {
            return "E";
        }
    }

    public String getPredikat(String nHuruf){
        switch (nHuruf) {
            case "A":
                return "Apik";
            case "AB":
                return "Apik Baik";
            case "B":
                return "Baik";
            case "BC":
                return "Baik Cukup";
            case "C":
                return "Cukup";
            case "D":
                return "Dableq";
            default:
                return "Elek";
        }
    }
}
