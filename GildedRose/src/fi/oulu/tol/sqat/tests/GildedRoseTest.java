package fi.oulu.tol.sqat.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import fi.oulu.tol.sqat.GildedRose;
import fi.oulu.tol.sqat.Item;

public class GildedRoseTest {

	@Test
	public void testTheTruth() {
		assertTrue(true);
	}
	
	@Test
	public void testGeneralItemQualityDecreaseByOneBeforeSellIn() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("+5 Dexterity Vest", 10, 20));
		inn.oneDay();
		
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		
		assertEquals("Failed quality for Dexterity Vest", 19, quality);
	}
	
	@Test 
	public void testGeneralItemQualityDecreaseByTwoAfterSellIn() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Elixir of the Mongoose", 0, 7));
		inn.oneDay();
		
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		
		assertEquals("Failed quality for the item", 5, quality);
	}
	
	@Test
	public void testQualityNeverNegative() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Elixir of the Mongoose", 0, 0));
		inn.oneDay();
		
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		
		assertEquals("Failed quality for the item", 0, quality);
	}
	
	@Test
	public void testAgedBrieQualityIncreaseWithAge() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Aged Brie", 2, 0));
		inn.oneDay();
		
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		
		assertEquals("Failed quality increase for Aged Brie", 1, quality);
	}
	
	
	@Test
	public void testItemQualityNeverOver50() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Aged Brie", 2, 50));
		inn.oneDay();
		
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		
		assertEquals("Quality of Aged Brie over 50", 50, quality);
	}
	
	@Test
	public void testSulfurasNoSellInNoQualityDecrease() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
		inn.oneDay();
		
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		int sellIn = items.get(0).getSellIn();
		
		assertEquals("Failed quality for Sulfuras, Hand of Ragnaros", 80, quality);
		assertEquals("Failed sell in for Sulfuras, Hand of Ragnaros", 0, sellIn);
	}
	
	@Test
	public void testBackstagePassQualityIncreaseBy1() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
		inn.oneDay();
		
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		
		assertEquals("Failed quality for Backstage passes", 21, quality);
	}
	
	@Test
	public void testBackstagePassQualityIncreaseBy2WhenUnder10Days() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 10, 20));
		inn.oneDay();
		
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		
		assertEquals("Failed quality for Backstage passes", 22, quality);
	}
	
	@Test
	public void testBackstagePassQualityIncreaseBy3WhenUnder5Days() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 5, 20));
		inn.oneDay();
		
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		
		assertEquals("Failed quality for Backstage passes", 23, quality);
	}
	
	@Test
	public void testBackstagePassQualityDropsToZeroAfterConcert() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20));
		inn.oneDay();
		
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		
		assertEquals("Failed quality for Backstage passes", 0, quality);
	}
}
