
package com.example.hajibootjdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

// リポジトリであることを示すアノテーション
@Repository
//DBトランザクションの制御を行う
//メソッドが正常終了したらDBトランザクションをコミット、例外が発生したらロールバックする
@Transactional
public class JdbcRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public void count() {
		String sql = "SELECT count(*) FROM PLAN;";
		Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
		System.out.println("PLANテーブルからの行数は" + count + " 行");
	}
}
