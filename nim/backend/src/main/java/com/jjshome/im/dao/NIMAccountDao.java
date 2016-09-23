package com.jjshome.im.dao;

import com.jjshome.im.entity.NIMAccount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * NIMAccount dao
 * Created by liaohongwei on 2016/9/23.
 */
public interface NIMAccountDao extends CrudRepository<NIMAccount,Long> {

  @Query("select a from NIMAccount a where a.userId=:userId")
  public List<NIMAccount> findByUserId(@Param("userId") long userId);
}
