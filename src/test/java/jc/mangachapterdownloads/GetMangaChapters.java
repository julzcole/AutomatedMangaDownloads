package jc.mangachapterdownloads;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import jc.mangapageobjects.ChapterPage;
import mangachapter.testcomponents.BaseTest;

public class GetMangaChapters extends BaseTest {

	@Test(dataProvider = "getData")
	public void getOnePieceChapter(HashMap<String, String> input) throws IOException {
		homePage.getNewChapter();
		ChapterPage chapterPage = homePage.openChapter(input.get("mangaName"));
		chapterPage.getPageImages(input.get("folderName"));

	}

	@Test(dataProvider = "getData2")
	public void getJJKChapter(HashMap<String, String> input) throws IOException {
		homePage.getNewChapter();
		ChapterPage chapterPage = homePage.openChapter(input.get("mangaName"));
		chapterPage.getPageImages(input.get("folderName"));
	}

	@Test(dataProvider = "getData3")
	public void getMHAChapter(HashMap<String, String> input) throws IOException {
		homePage.getNewChapter();
		ChapterPage chapterPage = homePage.openChapter(input.get("mangaName"));
		chapterPage.getPageImages(input.get("folderName"));

	}
	
	@Test(dataProvider = "getData4")
	public void getCSMChapter(HashMap<String, String> input) throws IOException {
		homePage.getNewChapter();
		ChapterPage chapterPage = homePage.openChapter(input.get("mangaName"));
		chapterPage.getPageImages(input.get("folderName"));

	}

	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = dataReader(
				System.getProperty("user.dir") + "//src//test//java//mangachaptersdata//mangadata.json");
		return new Object[][] { { data.get(0) } };
	}

	@DataProvider
	public Object[][] getData2() throws IOException {
		List<HashMap<String, String>> data = dataReader(
				System.getProperty("user.dir") + "//src//test//java//mangachaptersdata//mangadata.json");
		return new Object[][] { { data.get(1) } };
	}

	@DataProvider
	public Object[][] getData3() throws IOException {
		List<HashMap<String, String>> data = dataReader(
				System.getProperty("user.dir") + "//src//test//java//mangachaptersdata//mangadata.json");
		return new Object[][] { { data.get(2) } };
	}
	
	@DataProvider
	public Object[][] getData4() throws IOException {
		List<HashMap<String, String>> data = dataReader(
				System.getProperty("user.dir") + "//src//test//java//mangachaptersdata//mangadata.json");
		return new Object[][] { { data.get(3) } };
	}
}
