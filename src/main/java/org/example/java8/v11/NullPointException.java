package org.example.java8.v11;

import java.util.Optional;

/**
 * @Author wangp
 * @Date 2020/3/26
 * @Version 1.0
 */
public class NullPointException {

    public static void main(String[] args) {
//        String insuranceName = getInsuranceName(new Person());
//        System.out.println(insuranceName);


//        String insuranceNameByDeepDoubts = getInsuranceNameByDeepDoubts(new Person());
//        System.out.println(insuranceNameByDeepDoubts);


//        String insuranceNameByMultExit = getInsuranceNameByMultExit(new Person());
//        System.out.println(insuranceNameByMultExit);


        String insuranceNameByMultExitByOptional = getInsuranceNameByOptional(new Person());
        System.out.println(insuranceNameByMultExitByOptional);

    }

    private static String getInsuranceNameByOptional(Person person){
        return Optional.ofNullable(person)
                .map(Person::getCar)
                .map(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("UNKNOWN");

    }

    private static String getInsuranceNameByMultExit(Person person){
       final String defaultValue = "UNKNOWN";
        if(null==person){
            return defaultValue;
        }
        if(person.getCar() == null){
            return defaultValue;
        }
        if(person.getCar().getInsurance() == null){
            return defaultValue;
        }
        return person.getCar().getInsurance().getName();
    }

    private static String getInsuranceNameByDeepDoubts(Person person){
        if(null!=person){
            if(null!=person.getCar()){
                Insurance insurance = person.getCar().getInsurance();
                if(insurance!=null){
                    return insurance.getName();
                }
            }
        }
        return "UNKNOWN";
    }

    private static String getInsuranceName(Person person){
        return person.getCar().getInsurance().getName();
    }
}
