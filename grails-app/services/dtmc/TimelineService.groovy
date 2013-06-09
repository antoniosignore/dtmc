package dtmc

import grails.plugin.cache.CacheEvict
import grails.plugin.cache.Cacheable
import org.example.SecUser
import org.grails.twitter.Status

class TimelineService {

    static transactional = false

    @CacheEvict('timeline')
    void clearTimelineCacheForUser(String username) {}

    @Cacheable('timeline')
    def getTimelineForUser(String username) {
        def per = SecUser.findByUsername(username)
        def query = Status.whereAny {
            author { username == per.username }
            if (per.followed) author in per.followed
        }.order 'dateCreated', 'desc'
        def messages = query.list(max: 10)
        messages
    }
}
