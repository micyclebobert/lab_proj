package com.lab.project;

public class Group {
    private String section;
    private String fieldNameRange;
    private String studentInfoRange;
    private String[] extraInfoRange;

    public Group(String section, String fieldNameRange, String studentInfoRange, String extraInfoRanges) {
        this.section = section;
        this.fieldNameRange = fieldNameRange.toUpperCase();
        this.studentInfoRange = studentInfoRange.toUpperCase();
        this.extraInfoRange = Commons.splitCommas(extraInfoRanges.toUpperCase());
    }

    public String getSection() {
        return section;
    }

    public String getFieldNameRange() {
        return fieldNameRange;
    }

    public String getStudentInfoRange() {
        return studentInfoRange;
    }

    public String getExtraInfoRange(int index) {
        return extraInfoRange[index];
    }

    public String[] getExtraInfoRange() {
        return extraInfoRange;
    }

    public String toString() {
        String s = "Section: " + section
                + ", Field Name Range: " + fieldNameRange
                + ", Student Info Range: " + studentInfoRange;
        if (extraInfoRange.length == 1)
            s += ", Extra Info Range: " + extraInfoRange[0];
        else if (extraInfoRange.length > 1)
            s += ", Extra Info Ranges: { " + Commons.stringFromArray(extraInfoRange) + " }";
        return s;
    }

    public void print() {
        System.out.println(this.toString());
    }

    public void validate() throws ApuException {
        if(Commons.isValidCellRange(fieldNameRange)) throw new ApuException("Invalid field range");
        // if (fieldNameRange.split("fieldNameRange"))
        try {
        } catch (Exception e) {
            throw new ApuException("Field Range Error");
        }
    }
}
