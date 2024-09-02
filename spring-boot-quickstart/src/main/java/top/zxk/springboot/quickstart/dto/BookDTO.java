package top.zxk.springboot.quickstart.dto;

public record BookDTO(Long id, String title, String author,double price) {
    public BookDTO{
        if (price < 0){
            throw new IllegalArgumentException("Price cannot be negative");
        }
    }
}
