package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            Item item = items[i];
            updateItemQuality(item);
        }
    }

    private void updateItemQuality(Item item) {
        if (!isAgedBrie(item)
                && !isBackstage(item)
                && !isSulfuras(item)) {

            if (item.quality > 0) {
                item.quality = item.quality - 1;
            }

            item.sellIn = item.sellIn - 1;

            if (item.sellIn < 0) {

                if (item.quality > 0) {
                    item.quality = item.quality - 1;
                }
            }
        }

        if (isAgedBrie(item)) {
            if (item.quality < 50) {
                item.quality = item.quality + 1;


            }
            item.sellIn = item.sellIn - 1;
            if (item.sellIn < 0) {
                if (item.quality < 50) {
                    item.quality = item.quality + 1;
                }
            }
        }
        if (isBackstage(item)) {
            if (item.quality < 50) {
                item.quality = item.quality + 1;

                    if (item.sellIn < 11) {
                        if (item.quality < 50) {
                            item.quality = item.quality + 1;
                        }
                    }

                    if (item.sellIn < 6) {
                        if (item.quality < 50) {
                            item.quality = item.quality + 1;
                        }
                    }
            }
            item.sellIn = item.sellIn - 1;
            if (item.sellIn < 0) {
                item.quality = item.quality - item.quality;
            }
        }



}

    private boolean isSulfuras(Item item) {
        return item.name.equals("Sulfuras, Hand of Ragnaros");
    }

    private boolean isBackstage(Item item) {
        return item.name.equals("Backstage passes to a TAFKAL80ETC concert");
    }

    private boolean isAgedBrie(Item item) {
        return item.name.equals("Aged Brie");
    }
}
