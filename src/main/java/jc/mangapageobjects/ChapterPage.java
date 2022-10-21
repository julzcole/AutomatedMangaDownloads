package jc.mangapageobjects;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class ChapterPage {
	WebDriver driver;
	String chapterName;

	public ChapterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".fixed-ratio-content")
	List<WebElement> pages;

	@FindBy(tagName = "h1")
	WebElement chapterTitleElement;

	public void getPageImages(String folderName) throws IOException {
		for (int i = 0; i < pages.size(); i++) {
			chapterName = chapterTitleElement.getText();
			WebElement page = pages.get(i);
			int pageNum = i + 1;
			Screenshot pageImg = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
					.coordsProvider(new WebDriverCoordsProvider()).takeScreenshot(driver, page);
			ImageIO.write(pageImg.getImage(), "png",
					new File("X:\\mangachapters\\" + folderName + "\\" + chapterName + " - page " + pageNum + ".png"));
		}
		if (chapterName == null) {
			System.out.println("Nothing was saved.");
		} else {
			System.out.println(chapterName + " was saved successfully!");
		}
	}
}
