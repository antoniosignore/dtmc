dataSource {
    //ORACLE
    dbCreate = "create-drop"
    url = "jdbc:postgresql://127.0.0.1:5432/dtmc"
    pooled = true
    username = "postgres"
    password = "nicholas"

    driverClassName = "org.postgresql.Driver"
    dialect = org.hibernate.dialect.PostgresPlusDialect
}

hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
//    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory' // Hibernate 3
    cache.region.factory_class = 'org.hibernate.cache.ehcache.EhCacheRegionFactory' // Hibernate 4
    singleSession = true // configure OSIV singleSession mode
    flush.mode = 'manual' // OSIV session flush mode outside of transactional context

    jdbc.use_get_generated_keys = true
}

// environment specific settings
environments {
    development {
        dataSource {
            dbCreate = "create-drop"
        }
    }
    test {
        dataSource {
            dbCreate = "create-drop"
        }
    }
    production {
        dataSource {
            dbCreate = "none"
        }
    }
}
