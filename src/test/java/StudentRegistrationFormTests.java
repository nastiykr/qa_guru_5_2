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
        String firstName = "Masha";
        String lastName = "Ivanova";
        String gender = "Female";
        String email = "ivanova@ya.ru";
        String phone = "9117008090";
        String month = "May";
        String year = "1990";
        String subject = "Social Studies";
        String hobby = "Music";
        String photoPath = "src/test/resources/photo.jpeg";
        String photoName = "photo.jpeg";
        String address = "Moscow";
        String state = "Haryana";
        String city = "Karnal";

        open("https://demoqa.com/automation-practice-form");
        $(".main-header").shouldHave(text("Practice Form"));

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $(byText(gender)).click();
        $("#userNumber").setValue(phone);

        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(month);
        $(".react-datepicker__year-select").selectOption(year);
        $(".react-datepicker__day.react-datepicker__day--011").click();   //or $(String.format("[aria-label='Choose Friday, %s %sth, %s']", "May", "11", "1990")).click();

        $("#subjectsInput").setValue("a");
        $(byText(subject)).click();

        $(byText(hobby)).click();

        $("#uploadPicture").uploadFile(new File(photoPath));

        $("#currentAddress").setValue(address);

        $("#state").click();
        $(byText(state)).click();

        $("#city").click();
        $(byText(city)).click();

        $("#submit").click();

        $(".table-responsive").shouldHave(
                text("Student Name " + firstName + " " + lastName),
                text("Student Email " + email),
                text("Gender " + gender),
                text("Mobile " + phone),
                text("Date of Birth " + "11 May,1990"),
                text("Subjects " + subject),
                text("Hobbies " + hobby),
                text("Picture " + photoName),
                text("Address " + address),
                text("State and City " + state + " " + city)
        );

        /*
        $(".modal-title").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").$(byText("Student Name")).parent().shouldHave(text(firstName), text(lastName));
        $(".table-responsive").$(byText("Student Email")).parent().shouldHave(text(email));
        $(".table-responsive").$(byText("Gender")).parent().shouldHave(text(gender));
        $(".table-responsive").$(byText("Mobile")).parent().shouldHave(text(phone));
        $(".table-responsive").$(byText("Date of Birth")).parent().shouldHave(text("11 May,1990"));
        $(".table-responsive").$(byText("Subjects")).parent().shouldHave(text(subject));
        $(".table-responsive").$(byText("Hobbies")).parent().shouldHave(text(hobby));
        $(".table-responsive").$(byText("Picture")).parent().shouldHave(text(photoName));
        $(".table-responsive").$(byText("Address")).parent().shouldHave(text(address));
        $(".table-responsive").$(byText("State and City")).parent().shouldHave(text(state + " " + city));
         */
    }
}
