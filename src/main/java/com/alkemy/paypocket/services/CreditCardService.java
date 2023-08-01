package com.alkemy.paypocket.services;

import com.alkemy.paypocket.dtos.CreditCardDto;
import com.alkemy.paypocket.entities.CreditCard;
import com.alkemy.paypocket.mappers.CreditCardMapper;
import com.alkemy.paypocket.message.ResponseData;
import com.alkemy.paypocket.repositories.CreditCardRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CreditCardService {

    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    CreditCardMapper creditCardMapper;

    public ResponseData<CreditCardDto> saveCreditCard(CreditCardDto creditCardDto){

        try {

            CreditCard newCreditCard = creditCardMapper.toCreditCard(creditCardDto);

            creditCardRepository.save(newCreditCard);



            return new ResponseData<>(creditCardMapper.toCreditCardDto(newCreditCard), "Tarjeta de credito dada de alta");

        }catch (Exception e){
            return new ResponseData<>(null, e.getMessage());
        }

    }


    public String deleteCreditCard(Integer cardId){

        try {

            CreditCard creditCard = creditCardRepository.findById(cardId).orElseThrow(() -> new EntityNotFoundException("Tarjeta no encontrada"));

            creditCard.setSoftDelete(true);

            creditCardRepository.save(creditCard);

            String Message = "Tarjeta Eliminada";

            return Message;

        }catch (Exception e){
            return e.getMessage();
        }

    }
}




