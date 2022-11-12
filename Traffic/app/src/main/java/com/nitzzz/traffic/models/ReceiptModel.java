package com.nitzzz.traffic.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ReceiptModel implements Serializable {

    //primary id
    public int id = 0;
    public boolean veh_no = false;
    public String vehicle_no, licence_no, full_name, mobile;
    public List<RuleModel> rule_list = new ArrayList<>();
    public double total_fine = 0;
    public long time = 0;
}
