package br.ce.wcaquino.tasks.functional;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TasksTest {
	
	private WebDriver mDriver;
	private String BASE_URL = "http://localhost:8001/tasks/";
	
	@Before
	public void setup() {
		mDriver = new ChromeDriver();
		mDriver.get(BASE_URL);
	}
	
	@Test
	public void shouldSaveTaskSuccessfuly() {
		
		mDriver.findElement(By.id("addTodo")).click();
		
		mDriver.findElement(By.id("task")).sendKeys("Task Test");
		mDriver.findElement(By.id("dueDate")).sendKeys("10/10/2040");
		
		mDriver.findElement(By.id("saveButton")).click();
		String message = mDriver.findElement(By.id("message")).getText();
		Assert.assertEquals("Success!", message);
	}
	
	@Test
	public void shouldNotSaveTaskWithoutDesc() {
		
		mDriver.findElement(By.id("addTodo")).click();
		mDriver.findElement(By.id("dueDate")).sendKeys("10/10/2040");
		mDriver.findElement(By.id("saveButton")).click();
		String message = mDriver.findElement(By.id("message")).getText();
		Assert.assertEquals("Fill the task description", message);
	}
	
	@Test
	public void shouldNotSaveTaskWithoutDate() {
		
		mDriver.findElement(By.id("addTodo")).click();
		mDriver.findElement(By.id("task")).sendKeys("Task Test");
		mDriver.findElement(By.id("saveButton")).click();
		String message = mDriver.findElement(By.id("message")).getText();
		Assert.assertEquals("Fill the due date", message);
	}
	
	@Test
	public void shouldNotSaveTaskOutdated() {
		
		mDriver.findElement(By.id("addTodo")).click();
		mDriver.findElement(By.id("task")).sendKeys("Task Test");
		mDriver.findElement(By.id("dueDate")).sendKeys("10/10/2010");
		mDriver.findElement(By.id("saveButton")).click();
		String message = mDriver.findElement(By.id("message")).getText();
		Assert.assertEquals("Due date must not be in past", message);
	}
	
	@After
	public void after() {
		mDriver.close();
	}

}
