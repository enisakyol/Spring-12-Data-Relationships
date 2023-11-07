package com.cydeo.boostrap;

import com.cydeo.entity.*;
import com.cydeo.enums.Status;
import com.cydeo.repository.CartRepository;
import com.cydeo.repository.ItemRepository;
import com.cydeo.repository.MerchantRepository;
import com.cydeo.repository.PaymentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

@Component
public class DataGenerator implements CommandLineRunner {

private final PaymentRepository paymentRepository;
private final MerchantRepository merchantRepository;

private final ItemRepository itemRepository;
private final CartRepository cartRepository;

    public DataGenerator(PaymentRepository paymentRepository, MerchantRepository merchantRepository, ItemRepository itemRepository, CartRepository cartRepository) {
        this.paymentRepository = paymentRepository;
        this.merchantRepository = merchantRepository;
        this.itemRepository = itemRepository;
        this.cartRepository = cartRepository;
    }


    @Override
    public void run(String... args) throws Exception {


        Payment payment1 = new Payment(LocalDate.of(2022,4,19), new BigDecimal("150000"), Status.SUCCESS);
        PaymentDetail paymentDetail1 = new PaymentDetail(new BigDecimal("14000"), new BigDecimal("14444"), LocalDate.of(2000,1,1));
        payment1.setPaymentDetail(paymentDetail1);

        Payment payment2 = new Payment(LocalDate.of(2021,5,1), new BigDecimal("220000"), Status.FAILURE);
        PaymentDetail paymentDetail2 = new PaymentDetail(new BigDecimal("555000"), new BigDecimal("55555555"), LocalDate.of(2004,4,4));




        payment2.setPaymentDetail(paymentDetail2);

        Merchant merchant1 = new Merchant("Jules", "abd", new BigDecimal("122"), new BigDecimal("6755"), 12333);

        merchantRepository.save(merchant1);

paymentRepository.save(payment1);
paymentRepository.save(payment2);

Item item1 = new Item("Milk", "M01");
Item item2 = new Item("Sugar", "S01");

Cart cart1 = new Cart();
Cart cart2 = new Cart();

cart1.setItemList(Arrays.asList(item1));
cart2.setItemList(Arrays.asList(item1, item2));

itemRepository.save(item1);
itemRepository.save(item2);

cartRepository.save(cart1);
cartRepository.save(cart2);



        System.out.println(paymentRepository.findById(2L).get().getPaymentDetail().getCommissionAmount());


        paymentRepository.delete(payment1);


    }
}
