package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GildedRoseTest {


    private static final int MIN_QUALITY = 0;
    private static final int MAX_QUALITY = 50;

    @Test
    public void regular_items_quality_decreases_every_day() {
        Item item = regularItem(1, 3);
        GildedRose shop = new GildedRose(new Item[]{item});

        shop.updateQuality();

        assertEquals(item.quality, 2);
        assertEquals(item.sellIn, 0);
    }

    @Test
    public void expired_regular_items_quality_decreases_twice_as_fast_once_sellIn_has_passed() {
        Item item = regularItem(0, 3);
        GildedRose shop = new GildedRose(new Item[]{item});

        shop.updateQuality();

        assertEquals(item.quality, 1);
        assertEquals(item.sellIn, -1);
    }

    @Test
    public void regular_items_quality_never_decreases_below_the_minimum_quality() {
        Item item = regularItem(5, 0);
        GildedRose shop = new GildedRose(new Item[]{item});

        shop.updateQuality();

        assertEquals(item.quality, MIN_QUALITY);
        assertEquals(item.sellIn, 4);
    }

    @Test
    public void expired_regular_items_quality_never_decreases_below_the_minimum_quality() {
        Item item = regularItem(0, 1);
        GildedRose shop = new GildedRose(new Item[]{item});

        shop.updateQuality();

        assertEquals(item.quality, MIN_QUALITY);
        assertEquals(item.sellIn, -1);
    }

    @Test
    public void aged_brie_increases_quality_by_1() {
        Item item = agedBrie(5, 8);
        GildedRose shop = new GildedRose(new Item[]{item});

        shop.updateQuality();

        assertEquals(item.quality, 9);
        assertEquals(item.sellIn, 4);
    }

    @Test
    public void aged_brie_increases_quality_by_1_dup() {
        Item item = agedBrie(1, 8);
        GildedRose shop = new GildedRose(new Item[]{item});

        shop.updateQuality();

        assertEquals(item.quality, 9);
        assertEquals(item.sellIn, 0);
    }

    @Test
    public void aged_brie_quality_never_increases_over_the_maximun_quality() {
        Item item = agedBrie(5, MAX_QUALITY);
        GildedRose shop = new GildedRose(new Item[]{item});

        shop.updateQuality();

        assertEquals(item.quality, MAX_QUALITY);
        assertEquals(item.sellIn, 4);
    }

    @Test
    public void expired_aged_brie_quality_never_increases_over_the_maximun_quality() {
        Item item = agedBrie(0, MAX_QUALITY - 1);
        GildedRose shop = new GildedRose(new Item[]{item});

        shop.updateQuality();

        assertEquals(item.quality, MAX_QUALITY);
        assertEquals(item.sellIn, -1);
    }

    @Test
    public void expired_aged_brie_increases_quality_by_2() {
        Item item = agedBrie(0, 8);
        GildedRose shop = new GildedRose(new Item[]{item});

        shop.updateQuality();

        assertEquals(item.quality, 10);
        assertEquals(item.sellIn, -1);
    }

    @Test
    public void sulfuras_never_changes() {
        Item item = sulfuras(1, 80);
        GildedRose shop = new GildedRose(new Item[]{item});

        shop.updateQuality();

        assertEquals(item.quality, 80);
        assertEquals(item.sellIn, 1);
    }

    @Test
    public void backstage_increase_quality_by_1_when_sellIn_is_greater_than_10() {
        Item item = backstagePasses(11, 6);
        GildedRose shop = new GildedRose(new Item[]{item});

        shop.updateQuality();

        assertEquals(item.quality, 7);
        assertEquals(item.sellIn, 10);
    }

    @Test
    public void backstage_increase_quality_by_2_when_sellIn_is_between_10_and_6() {
        Item item1 = backstagePasses(10, 6);
        Item item2 = backstagePasses(6, 20);
        GildedRose shop = new GildedRose(new Item[]{item1, item2});

        shop.updateQuality();

        assertEquals(item1.quality, 8);
        assertEquals(item1.sellIn, 9);
        assertEquals(item2.quality, 22);
        assertEquals(item2.sellIn, 5);
    }

    @Test
    public void backstage_increase_quality_by_3_when_sellIn_is_between_5_and_1() {
        Item item1 = backstagePasses(5, 6);
        Item item2 = backstagePasses(1, 20);
        GildedRose shop = new GildedRose(new Item[]{item1, item2});

        shop.updateQuality();

        assertEquals(item1.quality, 9);
        assertEquals(item1.sellIn, 4);
        assertEquals(item2.quality, 23);
        assertEquals(item2.sellIn, 0);
    }

    @Test
    public void backstage_quality_drops_to_0_after_the_concert() {
        Item item = backstagePasses(0, 6);
        GildedRose shop = new GildedRose(new Item[]{item});

        shop.updateQuality();

        assertEquals(item.quality, 0);
        assertEquals(item.sellIn, -1);
    }

    @Test
    public void backstage_quality_never_increases_over_the_maximun_quality_when_increasing_by_1() {
        Item item = backstagePasses(11, MAX_QUALITY);
        GildedRose shop = new GildedRose(new Item[]{item});

        shop.updateQuality();

        assertEquals(item.quality, MAX_QUALITY);
        assertEquals(item.sellIn, 10);
    }

    @Test
    public void backstage_quality_never_increases_over_the_maximun_quality_when_increasing_by_2() {
        Item item = backstagePasses(8, MAX_QUALITY - 1);
        GildedRose shop = new GildedRose(new Item[]{item});

        shop.updateQuality();

        assertEquals(item.quality, MAX_QUALITY);
        assertEquals(item.sellIn, 7);
    }

    @Test
    public void backstage_quality_never_increases_over_the_maximun_quality_when_increasing_by_3() {
        Item item = backstagePasses(4, MAX_QUALITY - 2);
        GildedRose shop = new GildedRose(new Item[]{item});

        shop.updateQuality();

        assertEquals(item.quality, MAX_QUALITY);
        assertEquals(item.sellIn, 3);
    }

    public Item regularItem(int sellIn, int quality) {
        return new Item("foo", sellIn, quality);
    }

    public Item agedBrie(int sellIn, int quality) {
        return new Item("Aged Brie", sellIn, quality);
    }

    public Item sulfuras(int sellIn, int quality) {
        return new Item("Sulfuras, Hand of Ragnaros", sellIn, quality);
    }

    public Item backstagePasses(int sellIn, int quality) {
        return new Item("Backstage passes to a TAFKAL80ETC concert", sellIn, quality);
    }


}
