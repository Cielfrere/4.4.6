package utils;

import configuration.ApiRequestLogic;
import configuration.RequestBuilder;
import entity.Authors;
import models.request.SaveAuthors;
import models.request.SaveBooks;

import static io.restassured.RestAssured.given;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

public class DataHelper {

    public static Authors notSavedAuthor() {
        return new Authors(
                -6,
                "��",
                "��",
                "��");
    }

    public static Authors savedAuthor() {
        String firstName = randomAlphabetic(5);
        String secondName = randomAlphabetic(5);
        String familyName = randomAlphabetic(5);
        SaveAuthors saveAuthors = new SaveAuthors(firstName, secondName, familyName);
        models.responses.SaveAuthors saveAuthorsResponse = given()
                .spec(RequestBuilder.requestSaveAuthorSpecification(saveAuthors))
                .post()
                .as(models.responses.SaveAuthors.class);
        Authors authors = new Authors(
                saveAuthorsResponse.getAuthorId(),
                firstName,
                secondName,
                familyName
        );
        SaveBooks saveBooks = new SaveBooks();
        saveBooks.setBookTitle("���������� ��������");
        saveBooks.setAuthor(authors);
        ApiRequestLogic.saveBooks(saveBooks);

        return authors;
    }

    public static Authors withoutBookJson() {
        String firstName = randomAlphabetic(5);
        String secondName = randomAlphabetic(5);
        String familyName = randomAlphabetic(5);
        SaveAuthors requestSaveAuthors = new SaveAuthors(firstName, secondName, familyName);
        models.responses.SaveAuthors responseSaveAuthors = given()
                .spec(RequestBuilder
                        .requestSaveAuthorSpecification(requestSaveAuthors))
                .post()
                .as(models.responses.SaveAuthors.class);
        Authors authors = new Authors(responseSaveAuthors.getAuthorId(), firstName, secondName, familyName);
        return authors;
    }

    public static Authors withoutBookXml() {
        String firstName = randomAlphabetic(5);
        String secondName = randomAlphabetic(5);
        String familyName = randomAlphabetic(5);
        SaveAuthors requestSaveAuthors = new SaveAuthors(firstName, secondName, familyName);
        models.responses.SaveAuthors responseSaveAuthors = given()
                .spec(RequestBuilder.requestSaveAuthorSpecification(requestSaveAuthors))
                .post()
                .as(models.responses.SaveAuthors.class);
        Authors authors = new Authors(responseSaveAuthors.getAuthorId(), firstName, secondName, familyName);
        return authors;
    }

    public static SaveBooks withoutAuthorId() {
        Authors authors = new Authors();
        authors.setFirstName(randomAlphabetic(5));
        authors.setFamilyName(randomAlphabetic(5));
        authors.setSecondName(randomAlphabetic(5));
        SaveBooks requestSaveBooks = new SaveBooks();
        requestSaveBooks.setBookTitle("��������");
        requestSaveBooks.setAuthor(authors);
        return requestSaveBooks;

    }
}
