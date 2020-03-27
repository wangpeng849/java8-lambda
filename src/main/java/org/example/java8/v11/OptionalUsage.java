package org.example.java8.v11;

import java.util.Optional;

/**
 * @Author wangp
 * @Date 2020/3/26
 * @Version 1.0
 */
public class OptionalUsage {

    public static void main(String[] args) {
        /**********************
         *  1.构造方式 empty() *
         *  *******************
         */
        Optional<Insurance> insuranceOptional1 = Optional.empty();
        //insuranceOptional.get();         //java.util.NoSuchElementException: No value present

        /**********************
         *  2.构造方式 of()    *
         *  *******************
         */
        Optional<Insurance> insuranceOptional2 = Optional.of(new Insurance());
        //System.out.println(insuranceOptional2 == insuranceOptional1); //false

        /***************************
         *  3.构造方式 ofNullable() *
         *  ***********************
         */
        Optional<Insurance> insuranceOptional3 = Optional.ofNullable(null);
        System.out.println(insuranceOptional1 == insuranceOptional3); //true
/*
        Insurance insurance1 = insuranceOptional3.orElseGet(Insurance::new);
        Insurance insurance2 = insuranceOptional3.orElse(new Insurance());
        Insurance insurance3 = insuranceOptional3.orElseThrow(RuntimeException::new);
        Insurance insurance4 = insuranceOptional3.orElseThrow(() -> new RuntimeException("occur exception."));
*/
        Optional<Insurance> insuranceOptional4 = Optional.ofNullable(new Insurance());
        System.out.println(insuranceOptional2 == insuranceOptional4); //false


//        Insurance insurance1 = insuranceOptional2
//                .filter(insurance -> insurance.getName() != null)
//                .get();
//        System.out.println(insurance1);

/*
        Optional<String> nameOptional = insuranceOptional2
                .map(i -> i.getName());
        System.out.println(nameOptional.orElse("empty value"));
        System.out.println(nameOptional.isPresent());

        nameOptional.ifPresent(System.out::println);
 */
        String insuranceName = getInsuranceName(null);
        System.out.println(insuranceName);
        String insuranceNameByOptional = getInsuranceNameByOptional(null);
        System.out.println(insuranceNameByOptional);


        Optional.ofNullable(getInsuranceNameByOptionObj(new OptionalPerson())).ifPresent(System.out::println);
    }


    private static String getInsuranceName(Insurance insurance){
        if(null == insurance){
            return "UNKNOWN";
        }
        return insurance.getName();
    }

    private static String getInsuranceNameByOptional(Insurance insurance){
       return Optional.ofNullable(insurance)
                .map(Insurance::getName)
                .orElse("UNKNOWN")
        ;
    }

    private static String getInsuranceNameByOptionObj(OptionalPerson person){
        return Optional.ofNullable(person)
//                .map(OptionalPerson::getCar)
//                .map(OptionalCar::getInsurance)
                .flatMap(OptionalPerson::getCar)
                .flatMap(OptionalCar::getInsurance)
                .map(Insurance::getName)
                .orElse("UNKNOWN")
                ;

    }
}
