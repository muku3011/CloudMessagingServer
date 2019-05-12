package com.notifire.server.dao

import com.notifire.server.model.Server
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ServerRepository : CrudRepository<Server, String>
