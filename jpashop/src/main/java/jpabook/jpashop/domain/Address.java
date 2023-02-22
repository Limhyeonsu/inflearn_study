package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

//값 타입 클래스는 변경 불가능하게 작성해야 한다. 따라서 Setter는 만들지 않는다.
@Embeddable
@Getter
public class Address {
    private String city;
    private String street;
    private String zipcode;

    //JPA 스펙상 엔티티나 임베디드 타입은 자바 기본 생성자를 설정해야 한다.
    protected Address() {

    }
    //값 타입의 경우 생성은 생성자로만 한다
    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
