import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class StudentRegistrationFormTests {


    @BeforeAll
    static void setup() {
        Configuration.startMaximized = true;
    }

    @Test
    public void registrationTest() {
        String firstName = "masha";
        String lastName = "ivanova";
        String gender = "Female";
        String email = "ivanova@ya.ru";
        String phone = "9117008090";
        String month = "May";
        String year = "1990";
        String photoName = "photo.jpeg";
        String address = "Moscow";

        open("https://demoqa.com/automation-practice-form");
        $(".main-header").shouldHave(text("Practice Form"));

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $("#gender-radio-2").doubleClick();   // or $(byText(gender)).click();
        $("#userNumber").setValue(phone);

        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(month);
        $(".react-datepicker__year-select").selectOption(year);
        $(".react-datepicker__day.react-datepicker__day--011").click();   //or $(String.format("[aria-label='Choose Friday, %s %sth, %s']", "May", "11", "1990")).click();

        $("#subjectsInput").setValue("a");
        $("#react-select-2-option-3").click();

        $("[for='hobbies-checkbox-3']").click();   //or $(byText("Music")).click();

        $("#uploadPicture").uploadFile(new File(photoName));

        $("#currentAddress").setValue(address);

        $("#state").click();
        $("#react-select-3-option-2").click();

        $("#city").click();
        $("#react-select-4-option-0").click();

        $("#submit").click();

        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").$(byText("Student Name")).parent().shouldHave(text(firstName), text(lastName));
        $(".table-responsive").$(byText("Student Email")).parent().shouldHave(text(email));
        $(".table-responsive").$(byText("Gender")).parent().shouldHave(text(gender));
        $(".table-responsive").$(byText("Mobile")).parent().shouldHave(text(phone));
        $(".table-responsive").$(byText("Date of Birth")).parent().shouldHave(text("11 May,1990"));
        $(".table-responsive").$(byText("Subjects")).parent().shouldHave(text("Social Studies"));
        $(".table-responsive").$(byText("Hobbies")).parent().shouldHave(text("Music"));
        $(".table-responsive").$(byText("Picture")).parent().shouldHave(text(photoName));
        $(".table-responsive").$(byText("Address")).parent().shouldHave(text(address));
        $(".table-responsive").$(byText("State and City")).parent().shouldHave(text("Haryana Karnal"));
    }
}
