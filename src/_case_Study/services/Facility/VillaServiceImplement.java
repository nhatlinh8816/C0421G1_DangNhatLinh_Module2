package _case_Study.services.Facility;

import _case_Study.Check.CheckInputService;
import _case_Study.Exception.Choice;
import _case_Study.models.House;
import _case_Study.models.Room;
import _case_Study.models.Villa;
import _case_Study.utils.ReadAndWriteFile;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class VillaServiceImplement implements VillaService{
    public static Scanner input = new Scanner(System.in);
    private static final String VILLA_FILE_PATH = "src\\_case_Study\\data\\villa.csv";
    private static Map<Villa,Integer> villaIntegerMap =new LinkedHashMap<>();

    public Map<Villa, Integer> readDataVilla() {
        villaIntegerMap = new ReadAndWriteFile<Villa>().readMapData(VILLA_FILE_PATH);
        return villaIntegerMap;
    }

    @Override
    public List readData() {
        return null;
    }


    @Override
    public void add() {
        boolean checkName = false;
        String name = null;
        while (!checkName ){
            System.out.println("Nhập tên dịch vụ theo định dạng SVVL-YYYY, với YYYY là các số từ 0-9");
            name = input.nextLine();
            if (!villaIntegerMap.isEmpty()){
                for (Villa villa: villaIntegerMap.keySet()){
                    if (!villa.getName().equals(name)){
                        checkName= new CheckInputService().checkVilla(name);
                        break;
                    }
                }
            }else {
                checkName= new CheckInputService().checkVilla(name);
            }
            if (!checkName){
                System.out.println("Mã dịch vụ sai định dạng hoặc đã bị trùng với mã trước đó");
            }
        }

        boolean checkArea = false;
        float area = 0;
        while (!checkArea){
            System.out.println("Nhập diện tích sử dụng");
            area = Integer.parseInt(input.nextLine());
            if (area<=30){
                System.out.println("Diện tích phải lớn hơn 30 m2");
                checkArea = false;
            }else {
                checkArea = true;
            }
        }

        boolean checkCost = false;
        int cost= 0;
        while (!checkCost){
            System.out.println("Nhập chi phí thuê phòng");
            cost = Integer.parseInt(input.nextLine());
            if (cost<=0){
                System.out.println("Chi phí thuê phòng phải lớn hơn 0");
                checkCost = false;
            }else {
                checkCost = true;
            }
        }

        boolean checkMaximumPeople = false;
        int maximumPeople = 0;
        while (!checkMaximumPeople){
            System.out.println("Nhập số người thuê phòng");
            maximumPeople = Integer.parseInt(input.nextLine());
            if (maximumPeople<=0||maximumPeople>20){
                System.out.println("số người thuê phòng phải nằm trong khoảng 1 đến 20 người");
                checkMaximumPeople = false;
            }else {
                checkMaximumPeople = true;
            }
        }

        String typeOfRent = null;
        boolean checkOfRent = false;
        while (!checkOfRent){
            System.out.println("Nhập kiểu thuê theo định dạng XXX-YYYY, với YYYY là các số từ 0-9");
            typeOfRent = input.nextLine();
            checkOfRent = new CheckInputService().checkTypeOfRent(typeOfRent);
            if (!checkOfRent){
                System.out.println("Kiểu thuê không đúng định dạng");
            }
        }

        String standardOfRent = null;
        boolean checkStandard = false;
        while (!checkStandard){
            System.out.println("Nhập tiêu chuẩn theo định dạng STVL-YYYY, với YYYY là các số từ 0-9");
            standardOfRent = input.nextLine();
            checkStandard = new CheckInputService().checkStandardVilla(standardOfRent);
            if (!checkStandard){
                System.out.println("Tiêu chuẩn không đúng định dạng");
            }
        }

        boolean checkAreaOfPool = false;
        float areaOfPool = 0;
        while (!checkAreaOfPool){
            System.out.println("Nhập diện tích bể bơi sử dụng");
            areaOfPool = Integer.parseInt(input.nextLine());
            if (areaOfPool<=30){
                System.out.println("Diện tích bể bơi phải lớn hơn 30 m2");
                checkAreaOfPool = false;
            }else {
                checkAreaOfPool = true;
            }
        }
        boolean checkFloor = false;
        int floor = 0;
        while (!checkFloor){
            System.out.println("Nhập số tầng thuê");
            floor = Integer.parseInt(input.nextLine());
            if (floor<=0){
                System.out.println("số tầng thuê phải lớn hơn 0");
                checkFloor=false;
            }else {
                checkFloor = true;
            }
        }
        Villa newVilla = new Villa(name,area,cost,maximumPeople,typeOfRent,standardOfRent,areaOfPool,floor);
        villaIntegerMap.put(newVilla,0);
        new ReadAndWriteFile<Villa>().writeMapData(VILLA_FILE_PATH, villaIntegerMap);
    }



    @Override
    public void edit() {
        new VillaServiceImplement().readData();
        boolean check = false;
        String editName = null;
        while (!check) {
            System.out.println(" Nhập mã dịch vụ muốn sửa ");
            editName = input.nextLine();
            check = new CheckInputService().checkVilla(editName);
        }
        for (Villa villa : villaIntegerMap.keySet()) {
            if (villa.getName().equals(editName)) {
                float newArea = villa.getArea();
                float newCost = villa.getCost();
                int newMaxNumsPeople = villa.getMaximumPeople();
                String newTypeOfRent = villa.getRentalType();
                String newStandardOfRent = villa.getStandard();
                float newAreaOfPool = villa.getAreaOfPool();
                int newFloor = villa.getNumberOfFlour();
                int choice = 0;
                while (choice!=8) {
                    System.out.println("Nhập thuộc tính muốn thay đổi");
                    System.out.println("1. Diện tích");
                    System.out.println("2. Chi phí thuê");
                    System.out.println("3. Số người thuê");
                    System.out.println("4. Kiểu thuê ");
                    System.out.println("5. Tiêu chuẩn thuê ");
                    System.out.println("6. Diện tích bể bơi");
                    System.out.println("7. Số tầng thuê");
                    System.out.println("8.Kết thúc chỉnh sửa");
                    choice = new Choice().choiceInteger();
                    switch (choice) {
                        case 1: {
                            boolean checkArea = true;
                            while (checkArea) {
                                System.out.println("Nhập diện tích mới");
                                newArea = new Choice().choiceFloat();
                                if (newArea >= 30) {
                                    checkArea = false;
                                }
                            }
                            break;
                        }
                        case 2: {
                            boolean checkCost = true;
                            while (checkCost) {
                                System.out.println(" Nhập chi phí thuê mới");
                                newCost = new Choice().choiceFloat();
                                if (newCost > 0) {
                                    checkCost = false;
                                }
                            }
                            break;
                        }
                        case 3: {
                            boolean checkNumberPeople = true;
                            while (checkNumberPeople) {
                                System.out.println(" Nhập số người thuê mới");
                                newMaxNumsPeople = new Choice().choiceInteger();
                                if (newMaxNumsPeople <= 20 && newMaxNumsPeople > 0) {
                                    checkNumberPeople = false;
                                }
                            }
                            break;
                        }
                        case 4: {
                            boolean checkTypeOfRent = false;
                            while (!checkTypeOfRent) {
                                System.out.println(" Nhập kiểu thuê mới");
                                newTypeOfRent = input.nextLine();
                                checkTypeOfRent = new CheckInputService().checkTypeOfRent(newTypeOfRent);
                            }
                            break;
                        }

                        case 5: {
                            boolean checkStandard = false;
                            while (!checkStandard) {
                                System.out.println("Nhập tiêu chuẩn phòng mới");
                                newStandardOfRent= input.nextLine();
                                checkStandard = new CheckInputService().checkStandardHouse(newStandardOfRent);
                            }
                            break;
                        }

                        case 6: {
                            boolean checkAreaOfPool = true;
                            while (checkAreaOfPool) {
                                System.out.println("Nhập diện tích bể bơi mới");
                                newAreaOfPool = new Choice().choiceFloat();
                                if (newAreaOfPool >= 30) {
                                    checkAreaOfPool = false;
                                }
                            }
                            break;
                        }
                        case 7: {
                            boolean checkFloor = true;
                            while (checkFloor) {
                                System.out.println("Nhập số tầng thuê mới");
                                newFloor = new Choice().choiceInteger();
                                if (newFloor > 0) {
                                    checkFloor = false;
                                }
                            }
                            break;
                        }
                        case 8: {
                            Villa editVilla = new Villa(editName, newArea, newCost, newMaxNumsPeople, newTypeOfRent, newStandardOfRent,newAreaOfPool,newFloor);
                            int value = villaIntegerMap.get(villa);
                            villaIntegerMap.remove(villa);
                            villaIntegerMap.put(editVilla, value);
                            new FacilityServiceImplement().edit();
                        }
                        default:{
                            break;
                        }
                    }
                }


            }
        }
        new ReadAndWriteFile<>().clearFile(VILLA_FILE_PATH);
        new ReadAndWriteFile<Villa>().writeMapData(VILLA_FILE_PATH, villaIntegerMap);
    }

    @Override
    public void display() {
        for (Map.Entry<Villa,Integer>entry: villaIntegerMap.entrySet()){
            System.out.println(entry.getKey()+" - "+entry.getValue());
        }
    }


    @Override
    public void updateData(String data) {

    }

    @Override
    public void maintenance() {

    }

    @Override
    public void displayMaintenance() {

    }
    public String checkDataBooking() {
        new VillaServiceImplement().readDataVilla();
        String id = null;
        boolean checkId = false;
        while (!checkId) {
            System.out.println("Nhập tên dịch vụ");
            id = input.nextLine();
            for (Villa element : villaIntegerMap.keySet()) {
                if (element.getName().equals(id) && villaIntegerMap.get(element) < 5) {
                    checkId = new CheckInputService().checkVilla(id);
                }
            }
            if (!checkId) {
                System.out.println(id + " khong co trong danh sach hoac dang trong qua trinh bao trì!!!");
            }
        }
        return id;
    }
}
