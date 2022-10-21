package jc.mangapageobjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Homepage {
	WebDriver driver;
	ArrayList<String> newChapters = new ArrayList<String>();
	
	public Homepage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "div[class='bg-card rounded p-4 border border-border'] a[class='text-white text-lg font-bold']")
	List<WebElement> chapterLinks;

	@FindBy(css = ".bg-card")
	List<WebElement> chapters;

	@FindBy(css = "div[class='bg-card rounded p-4 border border-border'] time-ago")
	List<WebElement> timeSinceReleaseElement;

	@FindBy(css = ".fixed-ratio-content")
	List<WebElement> pages;

	public void getNewChapter() {
		for (int i = 0; i < chapters.size(); i++) {
			String chapterName = chapterLinks.get(i).getText();
			String timeSinceRelease = timeSinceReleaseElement.get(i).getText();
			if (!(timeSinceRelease.contains("day") || timeSinceRelease.contains("month"))) {
				newChapters.add(chapterName);
			}
			else if(timeSinceRelease.contains("yesterday")) {
				newChapters.add(chapterName);
			}

		}
		if (newChapters.isEmpty()) {
			System.out.println("There were no new releases!");
		} else {
			System.out.println("These are the new Chapters: " + newChapters);
		}
	}

	

	public ChapterPage openChapter(String mangaName) {
		for (int i = 0; i < newChapters.size(); i++) {
			if (newChapters.get(i).contains(mangaName)) {
				driver.findElement(By.xpath("//a[text()='" + newChapters.get(i) +"']")).click();
				break;
			}
			System.out.println("There were no new releases of " + mangaName);
		}
		
		return new ChapterPage(driver);
	}
}
