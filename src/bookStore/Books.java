package bookStore;

public class Books {
	String BookName, PublisherName, date;
	int  bookNumber, Quantity;
	float price;

	Books(String BookName, String PublisherName, String date, float price, int bookNumber, int Quantity) {
		this.BookName = BookName;
		this.PublisherName = PublisherName;
		this.date = date;
		this.price = price;
		this.bookNumber = bookNumber;
		this.Quantity = Quantity;
	}

}
