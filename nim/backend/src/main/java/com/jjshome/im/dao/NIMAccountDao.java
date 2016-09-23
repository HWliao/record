package com.jjshome.im.dao;

import com.jjshome.im.entity.NIMAccount;
import org.springframework.data.repository.CrudRepository;

/**
 * NIMAccount dao
 * Created by liaohongwei on 2016/9/23.
 */
public interface NIMAccountDao extends CrudRepository<NIMAccount,Long> {
}
