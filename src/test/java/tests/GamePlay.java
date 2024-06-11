package tests;

import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.MainPage;

public class GamePlay extends BaseTest {

    @BeforeMethod
    @Parameters({"browser", "wait", "env"})
    public void setup(String browser, String wait, String env) throws Exception {
        init(browser, wait);
        openApp(env);
    }

    @AfterMethod
    @Parameters({"quit"})
    public void tearDown(String quit) {
        if (quit.equalsIgnoreCase("Yes")) quit();
    }
    @Epic("Gameplay")
    @Feature("Playing the game")
    @Story("Starting new game ")
    @Test(description = "Starting new game when clicking on the new game button")
    @Description("Starting the new game")
    @Step("Starting new game")
    @Link("https://www.play2048.co/")
    @Issue("BD-275")
    @TmsLink("DEMO-1")
    @Severity(SeverityLevel.CRITICAL)
    public void newGame() throws Exception {
        MainPage mainPage = new MainPage(driver);
        mainPage.checkTitle("2048");
        mainPage.startNewGame();
        driver.switchTo().alert().accept();
        mainPage.checkTileCount("2");
        mainPage.startNewGame();
        driver.switchTo().alert().accept();
        mainPage.checkTileCount("2");
        reportScreenshot("New Game", "Starting new game");
    }

    @Epic("Gameplay")
    @Feature("Playing the game")
    @Story("Playing new game")
    @Test(description = "Playing new game with number of moves that we choose")
    @Description("Playing new game with desired number of moves")
    @Step("Playing new game when we input desired number of moves")
    @Link("https://www.play2048.co/")
    @Issue("BD-275")
    @TmsLink("DEMO-1")
    @Severity(SeverityLevel.CRITICAL)
    public void play() throws Exception {
        MainPage mainPage = new MainPage(driver);
        mainPage.play(50);
        mainPage.checkScore();
        reportScreenshot("Play Game With Desired Number Of Moves", "Playing the game");
    }

    @Epic("Gameplay")
    @Feature("Playing the game")
    @Story("Playing new game ")
    @Test(description = "Playing new game with set number of moves")
    @Description("Playing new game with predetermined number of moves")
    @Step("Playing the new game when the number of moves is already given")
    @Link("https://www.play2048.co/")
    @Issue("BD-275")
    @TmsLink("DEMO-1")
    @Severity(SeverityLevel.CRITICAL)
    public void fullGame() throws Exception {
        MainPage mainPage = new MainPage(driver);
        mainPage.playUntilGameOver();
        reportScreenshot("Play Game With Given Number Of Moves ", "Playing the game");
    }

    @Epic("Gameplay")
    @Feature("Playing the game")
    @Story("Playing new game")
    @Test(description = "Playing new game with set goal")
    @Description("Playing new game with predetermined goal")
    @Step("Playing the new game when the goal is already given")
    @Link("https://www.play2048.co/")
    @Issue("BD-275")
    @TmsLink("DEMO-1")
    @Severity(SeverityLevel.CRITICAL)
    public void fullGameWin() throws Exception {
        MainPage mainPage = new MainPage(driver);
        mainPage.playUntilGameOver("128");
        reportScreenshot("Play Game With Given Goal", "Playing the game");
    }

    @Epic("Gameplay")
    @Feature("Playing the game")
    @Story("Playing new game")
    @Test(description = "Playing new game over and over until goal is reached")
    @Description("Playing new game over and over again with predetermined goal")
    @Step("Playing the new game over and over again when the goal is already given")
    @Link("https://www.play2048.co/")
    @Issue("BD-275")
    @TmsLink("DEMO-1")
    @Severity(SeverityLevel.CRITICAL)
    public void fullGameTryAgain() throws Exception {
        MainPage mainPage = new MainPage(driver);
        mainPage.playUntilGameOverAndTryAgain("128");
        reportScreenshot("Play Game Over And Over Again Until Goal Is Reached", "Playing the game");
    }
}
