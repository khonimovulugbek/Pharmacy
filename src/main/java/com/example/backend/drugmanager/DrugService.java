package com.example.backend.drugmanager;


import com.example.backend.host.Host;
import com.google.zxing.EncodeHintType;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import javafx.scene.image.*;
import lombok.SneakyThrows;


;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.example.backend.MyQr.createQR;

public class DrugService {

    @SneakyThrows
    public void createDrugTable() {
        Host.statement.executeUpdate("create table if not exists getDrugs\n" +
                "(\n" +
                "    id           serial,\n" +
                "    name         text,\n" +
                "    type         varchar,\n" +
                "    dose         varchar,\n" +
                "    manufactured varchar,\n" +
                "    cost         numeric,\n" +
                "    amount       int,\n" +
                "    primary key (id)\n" +
                ")");
    }


    @SneakyThrows
    public void addDrugs(String name_d, String types, String doses, String manufactureds, Integer costs, Integer amounts) {
        Host.statement.executeUpdate("insert into getDrugs(name, type, dose, manufactured, cost, amount)\n" +
                "VALUES ('" + name_d + "', '" + types + "', '" + doses + "', '" + manufactureds + "', " + costs + ", " + amounts + ")");
    }

    public String drugInfo(String text) {

        return checkDrug(getDrugList(), text);
    }

    public String checkDrug(List<Drug> drugs, String drugname) {
        StringBuilder druginfo = new StringBuilder();
        String notfound = "drug is not found";
        for (Drug drug : drugs) {
            if (drug.getName().equalsIgnoreCase(drugname)) {

                druginfo.append(drug.getName());
                druginfo.append(" ");
                druginfo.append(drug.getManufactured());
                druginfo.append(" ");
                druginfo.append(drug.getType());
                druginfo.append(" ");
                druginfo.append(drug.getDose());
                return druginfo.toString();
            }
        }

        return notfound;
    }

    @SneakyThrows
    public List<Drug> getDrugList() {
        List<Drug> drugs = new LinkedList<>();
        ResultSet resultSet = Host.statement.executeQuery("select * from getdrugs");
        while (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String type = resultSet.getString("type");
            String dose = resultSet.getString("dose");
            String manufactured = resultSet.getString("manufactured");
            Integer cost = resultSet.getInt("cost");
            Integer amount = resultSet.getInt("amount");
            Drug drug = new Drug();
            drug.setId(id);
            drug.setName(name);
            drug.setType(type);
            drug.setDose(dose);
            drug.setManufactured(manufactured);
            drug.setCost(cost);
            drug.setAmount(amount);
            drugs.add(drug);
        }
        return drugs;
    }

    public Drug getDrug(String drugname) {
        for (Drug drug : getDrugList()) {
            if (drug.getName().equalsIgnoreCase(drugname)) {

                return drug;
            }
        }
        return null;
    }


    @SneakyThrows
    public void printcheque(List<Drug> drugList, List<Integer> drugcount) {
        String jpg = "logous.png";
        ImageData uslogo = ImageDataFactory.create(jpg);
        Image image = new Image(uslogo);

        LocalDateTime chequeTime = LocalDateTime.now();

        String pdfchek = "/home/khan/Desktop/cheque.pdf";
        PdfWriter pdfWriter = new PdfWriter(pdfchek);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        float height = checksize(drugcount);

        pdfDocument.addNewPage(new PageSize(125.0F, height));
        Document document = new Document(pdfDocument);

        StringBuilder drugs = tostringbuilder(drugList, drugcount);

        Paragraph dorilar = new Paragraph(String.valueOf(drugs)).setFontSize(5);

        Drugs_cheque_QR(drugs.toString());

        String qr = "drugQR.png";
        ImageData qrjpg = ImageDataFactory.create(qr);
        Image qrjpg1 = new Image(qrjpg);

        document.add(image);
        document.add(dorilar);
        document.add(qrjpg1);
        document.setFontSize(5);
        document.close();
    }


    public StringBuilder tostringbuilder(List<Drug> drugList, List<Integer> drugcount) {
        double sum = 0;
        double total;
        double drugcost;
        String nameDrug;
        String manufacture;

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < drugList.size(); i++) {

            drugcost = drugList.get(i).getCost();
            manufacture = drugList.get(i).getManufactured();
            nameDrug = drugList.get(i).getName();
            total = drugList.get(i).getCost() * drugcount.get(i);
            sum += total;

            stringBuilder.append(nameDrug);
            stringBuilder.append(" ");
            stringBuilder.append(manufacture);
            stringBuilder.append("\n");
            stringBuilder.append(drugcount.get(i).toString());
            stringBuilder.append(" x ");
            stringBuilder.append(String.valueOf(drugcost));
            stringBuilder.append(" =");
            stringBuilder.append(String.valueOf(total));
            stringBuilder.append("\n");
            stringBuilder.append("- - - - - - - - - - - - -\n");

        }

        stringBuilder.append("TOTAL :");
        stringBuilder.append(String.valueOf(sum));
        return stringBuilder;
    }


    public List<Drug> addedDrugList(Drug drug) {
        List<Drug> drugList = new LinkedList<>();
        drugList.add(drug);
        return drugList;

    }


    @SneakyThrows
    public void Drugs_cheque_QR(String drugsString) {

        String path = "drugQR.png";
        String charset = "UTF-8";

        Map<EncodeHintType, ErrorCorrectionLevel> hashMap
                = new HashMap<>();

        hashMap.put(EncodeHintType.ERROR_CORRECTION,
                ErrorCorrectionLevel.L);
        createQR(drugsString, path, charset, hashMap, 35, 35);

    }


    public float checksize(List<Integer> drugcount) {
        float size = 110;
        for (int i = 0; i < drugcount.size(); i++) {
            size += 40;
        }
        size += 50;
        return size;
    }


    public List<Integer> addedCountList(Integer count) {
        List<Integer> list = new LinkedList<>();
        list.add(count);
        return list;
    }
}
