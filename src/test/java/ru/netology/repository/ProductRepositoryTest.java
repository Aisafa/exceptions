package ru.netology.repository;

import org.junit.jupiter.api.Test;
import ru.netology.NotFoundException;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {
    private ProductRepository repository = new ProductRepository();
    private Book javaBook = new Book(1, "jabaBook", 1000, "Shildt");
    private Book javaCode = new Book(2, "jabaCde", 1000, "Code");
    private Smartphone iphoneX = new Smartphone(1, "iphoneX", 75000, "Apple");
    private Smartphone iphone12 = new Smartphone(2, "iphoneX", 99000, "Apple");


    @Test
    public void shouldSaveItems() {
        repository.save(iphoneX);
        repository.save(javaBook);
        Product[] expected = new Product[]{iphoneX, javaBook};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }
    @Test
    public void shouldFindAll() {
        repository.save(iphoneX);
        repository.save(javaBook);
        Product[] expected = new Product[]{iphoneX, javaBook};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }
    @Test
    public void shouldFindById() {
        int idToFind = 1;
        repository.save(iphoneX);
        repository.save(iphone12);
        repository.findById(idToFind);
        Product[] expected = new Product[]{iphoneX};
        Product[] actual = new Product[]{repository.findById(idToFind)};
        assertArrayEquals(expected, actual);
    }
    @Test
    public void shouldFindByNotId() {
        int idToFind = 3;
        repository.save(iphoneX);
        repository.save(javaBook);
        repository.findById(idToFind);
        Product[] expected = new Product[]{null};
        Product[] actual = new Product[]{repository.findById(idToFind)};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldRemoveById(){
        int idToRemove = 2;
        repository.save(iphoneX);
        repository.save(iphone12);
        repository.save(javaBook);
        repository.removeById(idToRemove);
        Product[] expected = new Product[]{iphoneX, javaBook};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldRemoveNotById() throws NotFoundException {
        int idToRemove = 3;
        repository.save(iphoneX);
        repository.save(javaBook);
        assertThrows(NotFoundException.class, () -> repository.removeById(idToRemove));
    }
}