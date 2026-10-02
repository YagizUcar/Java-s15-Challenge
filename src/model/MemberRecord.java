package model;

public class MemberRecord {
    private int memberId;
    private String type;
    private String dateOfMembership;
    private int noBooksIssued;
    private final int maxBookLimit = 5;
    private String name;
    private String address;
    private String phoneNo;

    public MemberRecord(int memberId, String type, String dateOfMembership, String name, String address, String phoneNo) {
        this.memberId = memberId;
        this.type = type;
        this.dateOfMembership = dateOfMembership;
        this.name = name;
        this.address = address;
        this.phoneNo = phoneNo;
        this.noBooksIssued = 0;
    }

    public int getMemberId() { return memberId; }
    public String getName() { return name; }
    public int getNoBooksIssued() { return noBooksIssued; }
    public void incBookIssued() { if (noBooksIssued < maxBookLimit) noBooksIssued++; }
    public void decBookIssued() { if (noBooksIssued > 0) noBooksIssued--; }
}