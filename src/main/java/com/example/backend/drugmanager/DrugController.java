package com.example.backend.drugmanager;


import com.example.backend.container.ComponentContainer;

import java.util.LinkedList;
import java.util.List;

public class DrugController {
    private List<Integer> sum = new LinkedList<>();
    private List<Drug> drugList = new LinkedList<>();

    public void addDrugs(Drug drug) {
        ComponentContainer.drugService.addDrugs(drug.getName(), drug.getType(), drug.getDose(), drug.getManufactured(), drug.getCost(), drug.getAmount());
    }

    public String drugInfo(String text) {
        return ComponentContainer.drugService.drugInfo(text);
    }

    public void addedDrugList(String text) {
        Drug drug = ComponentContainer.drugService.getDrug(text);
        drugList.add(drug);

    }

    public List<Drug> getDrugList() {
        return drugList;
    }

    public void addedCount(Integer count) {
        sum.add(count);
    }

    public List<Integer> getSum() {
        return sum;
    }
}
