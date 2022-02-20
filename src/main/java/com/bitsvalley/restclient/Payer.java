package com.bitsvalley.restclient;

public class Payer {
    String partyIdType = "MSISDN";
    String partyId;

    public Payer(String tel)
    {
       this.partyId = tel;
    }

    public Payer()
    {

    }

    public String getPartyIdType() {
        return partyIdType;
    }

    public void setPartyIdType(String partyIdType) {
        this.partyIdType = partyIdType;
    }

    public String getPartyId() {
        return partyId;
    }

    public void setPartyId(String partyId) {
        this.partyId = partyId;
    }
}
