/*
 * Copyright (c) 2014-2017 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 1/3/17 9:48 PM
 */

package com.thecoffeine.storage.models.repositories;

import com.thecoffeine.storage.models.entities.File;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository for store files.
 *
 * @version 1.0
 */
public interface FileRepository extends CrudRepository<File, Long> {

}
