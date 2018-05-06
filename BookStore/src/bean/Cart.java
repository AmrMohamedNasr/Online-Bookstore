package bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart {
	private List<CartItem> items;
	private Map<Integer, CartItem> map;
	public Cart() {
		items = new ArrayList<CartItem>();
		map = new HashMap<Integer, CartItem> ();
	}
	public void addToCart(Book book) {
		if (!map.containsKey(book.getIsbn())) {
			CartItem item = new CartItem(book, 1);
			items.add(item);
			map.put(book.getIsbn(), item);
		}
	}
	
	public void changeQuantity(int isbn, int quantity) {
		if (map.containsKey(isbn)) {
			CartItem item = map.get(isbn);
			if (quantity > 0) {
				item.setQuantity(quantity);
			} else {
				map.remove(isbn);
				items.remove(item);
			}
		}
	}
	public boolean hasBook(int isbn) {
		return map.containsKey(isbn);
	}
	public List<CartItem> getItems() {
		return items;
	}
	public void setItems(List<CartItem> items) {
		this.items = items;
	}
	public void clearCart() {
		map.clear();
		items.clear();
	}
}
