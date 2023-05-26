package com.example.demo.controller;

import com.example.demo.entity.Book;
import com.example.demo.services.BookService;
import com.example.demo.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private CategoryService categoryService;
    @GetMapping
    public String showAllBooks(Model model){
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books",books);
        return "book/list";
    }
    @GetMapping("/add")
    public String addBookForm(Model model){
        model.addAttribute("book", new Book());
        model.addAttribute("categories",categoryService.getAllCategories());
        return "book/add";
    }
    @PostMapping("/add")
    public String addBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            // Xử lý lỗi ở đây
            model.addAttribute("categories", categoryService.getAllCategories());
            return "book/add";
        }
        bookService.addBook(book);
        return "redirect:/books";
    }
    @GetMapping("/edit/{id}")
    public String editBooks(@PathVariable("id") Long id, Model model){
        for(Book book : bookService.getAllBooks()){
            if (book.getId().equals(id)){
                model.addAttribute("book", book);
                model.addAttribute("categories", categoryService.getAllCategories());
                return "book/edit";
            }
        }
        return "redirect:/books";
    }

    @PostMapping("/edit")
    public String editBooks(@ModelAttribute("book") Book updateBook) {
        // Logic để xử lý dữ liệu sách đã chỉnh sửa và lưu vào cơ sở dữ liệu
        bookService.updateBook(updateBook);

        // Chuyển hướng người dùng đến trang danh sách sách
        return "redirect:/books";
    }
    @GetMapping("/delete/{id}")
    public String deleteBooks(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }
}
