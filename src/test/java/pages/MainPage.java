package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;
import java.util.Random;

public class MainPage extends BasePage {

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".title")
    WebElement mainTitle;

    @FindBy(css = ".restart-button")
    WebElement newGame;

    @FindBy(css = ".tile-new")
    List<WebElement> newTiles;

    @FindBy(css = ".score-container")
    WebElement score;

    @FindBy(css = "body")
    WebElement board;

    @FindBy(xpath = "//p[text()='Game over!']")
    List<WebElement> gameOver;

    @FindBy(css = ".tile-inner")
    List<WebElement> tilesWithNumbers;

    @FindBy(css = ".retry-button")
    WebElement tryAgainButton;

    public void checkTitle(String title) {
        assertEQ(mainTitle.getText(), title, "Title");
    }

    public void startNewGame() throws Exception {
        click(newGame, "New Game Button");
    }

    public void checkTileCount(String tileNumber) {
        assertEQ(String.valueOf(newTiles.size()), tileNumber, "Tile Count");
    }

    public void checkScore() {
        Assert.assertTrue(!score.getText().equals("0") && !score.getText().isEmpty());
    }

    public void play(int numberOfMoves) throws Exception {
        Keys[] moves = {Keys.ARROW_UP, Keys.ARROW_DOWN, Keys.ARROW_LEFT, Keys.ARROW_RIGHT};
        Random random = new Random();
        for (int i = 1; i <= numberOfMoves; i++) {
            int randomMoves = random.nextInt(moves.length);
            typeText(board, moves[randomMoves], "Move: " + i);
        }
    }

    public void playUntilGameOver() throws Exception {
        Keys[] moves = {Keys.ARROW_UP, Keys.ARROW_DOWN, Keys.ARROW_LEFT, Keys.ARROW_RIGHT};
        Random random = new Random();
        while (true) {
            for (int i = 1; i <= 50; i++) {
                int randomMoves = random.nextInt(moves.length);
                typeText(board, moves[randomMoves], "Move: " + i);
            }
            if (!gameOver.isEmpty() && gameOver.get(0).isDisplayed()) {
                System.out.println(getCurrentTimeAndDate() + " Game Over!");
                break;
            } else {
                System.out.println("Game is still on!");
            }
        }

    }

    public void playUntilGameOver(String goal) throws Exception {
        Keys[] moves = {Keys.ARROW_UP, Keys.ARROW_DOWN, Keys.ARROW_LEFT, Keys.ARROW_RIGHT};
        Random random = new Random();
        main:
        while (true) {
            for (int i = 1; i <= 50; i++) {
                int randomMoves = random.nextInt(moves.length);
                typeText(board, moves[randomMoves], "Move: " + i);
            }
            if (!gameOver.isEmpty() && gameOver.get(0).isDisplayed()) {
                System.out.println(getCurrentTimeAndDate() + " Game Over!");
            }
            for (WebElement tilesWithNumber : tilesWithNumbers) {
                if (tilesWithNumber.getText().equals(goal)) {
                    System.out.println("Goal of " + goal + " has been reached! Congratulations and YIPPIEKAYAAAAAAAAAY!");
                    break main;
                }
            }
        }

    }

    public void playUntilGameOverAndTryAgain(String goal) throws Exception {
        Keys[] moves = {Keys.ARROW_UP, Keys.ARROW_DOWN, Keys.ARROW_LEFT, Keys.ARROW_RIGHT};
        Random random = new Random();
        System.out.println(getCurrentTimeAndDate() + " Game started!");
        main:
        while (true) {
            for (int i = 1; i <= 50; i++) {
                int randomMoves = random.nextInt(moves.length);
                typeText(board, moves[randomMoves], "Move: " + i);
            }
            if (!gameOver.isEmpty() && gameOver.get(0).isDisplayed()) {
                System.out.println(getCurrentTimeAndDate() + " It's time to play the game again!");
                click(tryAgainButton, "Try again button");

            }
            for (WebElement tilesWithNumber : tilesWithNumbers) {
                if (tilesWithNumber.getText().equals(goal)) {
                    System.out.println("Goal of " + goal + " has been reached! Congratulations and YIPPIEKAYAAAAAAAAAY!");
                    break main;
                }
            }
        }

    }
}
