package com.example.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.common.ApiResult;
import com.example.demo.entity.Book;
import com.example.demo.entity.Borrow;
import com.example.demo.mapper.BookMapper;
import com.example.demo.mapper.BorrowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class BorrowBookService {


    @Autowired
    private BorrowMapper borrowMapper;
    @Autowired
    private BookMapper bookMapper;

    @Transactional
    public ApiResult borrowBook(String userName,String bookName, String bookCode) {
        Borrow existingBorrow = borrowMapper.selectOne(new QueryWrapper<Borrow>()
                .eq("user_name", userName)
                .eq("book_name", bookName)
                .eq("lending_status", 0));

        if (existingBorrow != null) {
            return ApiResult.error("借书失败，您已经借过这本书");
        }
            Book book = bookMapper.selectOne(new QueryWrapper<Book>().eq("book_code", bookCode));
            if (book == null || book.getInventoryQuantity() <= 0) {
                return ApiResult.error("借书失败，库存不足");
            }
            book.setInventoryQuantity(book.getInventoryQuantity() - 1);
            bookMapper.updateById(book);
            int day = 3 * 24 * 60 * 60 * 1000;
            String lendingTime = String.valueOf(new Date(System.currentTimeMillis()).getTime());
            String repaymentTime = String.valueOf(new Date(System.currentTimeMillis()).getTime() + day);
            Borrow borrow = new Borrow();
            borrow.setUserName(userName);
            borrow.setBookName(bookName);
            borrow.setBookCode(bookCode);
            borrow.setLendingTime(lendingTime);
            borrow.setRepaymentTime(repaymentTime);
            int startStatus = 0;
            borrow.setLendingStatus(startStatus);
            borrowMapper.insert(borrow);
            return ApiResult.success("借书成功");
    }
    public ApiResult returnBook(String userName,String bookName, String bookCode) {
        Borrow borrow = borrowMapper.selectOne(new QueryWrapper<Borrow>().eq("user_name", userName).eq("book_name", bookName).eq("book_code", bookCode));
        if (borrow == null) {
            return ApiResult.error("还书失败，未借书");
        }
        //逾期
        int finallyStatus = -1;
        if (borrow.getLendingStatus() == finallyStatus) {
            return ApiResult.error("还书失败，已逾期");
        }
        //已归还
        int endStatus = 1;
        borrow.setLendingStatus(endStatus);
            borrowMapper.updateById(borrow);
            Book book = bookMapper.selectOne(new QueryWrapper<Book>().eq("book_code", bookCode));
            book.setInventoryQuantity(book.getInventoryQuantity() + 1);
            bookMapper.updateById(book);
            return ApiResult.success("还书成功");
    }
    @Scheduled(cron = "0 0 0 * * ?")
    public void handleOverdueRecords() {
        List<Borrow> overdueRecords = borrowMapper.selectList(new QueryWrapper<Borrow>().eq("lending_status", 0));
        String Time = String.valueOf(new Date(System.currentTimeMillis()).getTime());
        for (Borrow borrow : overdueRecords) {
            if (borrow.getRepaymentTime().compareTo(Time) < 0) {
                borrow.setLendingStatus(-1);
                borrowMapper.updateById(borrow);
            }
        }
    }

    public List<Borrow> getBorrowRecords(String userName) {
        return borrowMapper.selectList(new QueryWrapper<Borrow>().eq("user_name", userName));
    }
}

