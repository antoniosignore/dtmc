package arrested

import com.dtmc.club.Club
import com.dtmc.finance.finpojo.Portfolio
import com.dtmc.indicator.UserIndicators
import com.dtmc.trading.StrategyCatalog

class ArrestedUser {

    static belongsTo = [club: Club]

    String username
    String passwordHash
    Date dateCreated
    Date lastUpdated
    Long token

    String firstname
    String lastname
    String address1
    String address2
    String city
    String state
    String country
    String company
    String email
    String phone
    String mobile
    String twitter
    String facebook
    String linkedin
    String timezone

    static hasMany = [portfolios: Portfolio, followers: ArrestedUser, indicators: UserIndicators, strategies: StrategyCatalog]

    static constraints = {
        username blank: false, unique: true
        passwordHash blank: false
        dateCreated nullable: true
        lastUpdated nullable: true
        token nullable: true
        firstname(size: 1..30, blank: true, nullable: true)
        lastname(size: 1..30, blank: true, nullable: true)
        email(email: true, nullable: true)
        address1(email: true, blank: true, nullable: true)
        address2(email: true, blank: true, nullable: true)
        phone(size: 6..15, blank: true, nullable: true)
        mobile(size: 6..15, blank: true, nullable: true)
        city(size: 1..20, blank: true, nullable: true)
        state(size: 1..20, blank: true, nullable: true)
        country(size: 1..20, blank: true, nullable: true)
        twitter(size: 5..200, blank: true, nullable: true)
        facebook(size: 5..200, blank: true, nullable: true)
        linkedin(size: 5..200, blank: true, nullable: true)
        company(size: 5..200, blank: true, nullable: true)
        timezone(nullable: true)
    }

    def toObject() {
        return [id       : id,
                username : username,
                token    : ArrestedToken.get(token)?.token,
                firstname: firstname,
                lastname : lastname,
                address1 : address1,
                address2 : address2,
                city     : city,
                state    : state,
                country  : country,
                company  : company,
                email    : email,
                phone    : phone,
                mobile   : mobile,
                twitter  : twitter,
                facebook : facebook,
                linkedin : linkedin,
                timezone : timezone
        ]

    }

    def showInformation() {
        return [id      : id,
                username: username]
    }
}