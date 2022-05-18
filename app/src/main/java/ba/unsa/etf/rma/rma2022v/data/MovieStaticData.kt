package ba.unsa.etf.rma.rma2022v.data

fun favoriteMovies(): List<Movie> {
    return listOf(
        Movie(1,"Pride and prejudice",
            "Sparks fly when spirited Elizabeth Bennet meets single, rich, and proud Mr. Darcy. But Mr. Darcy reluctantly finds himself falling in love with a woman beneath his class. Can each overcome their own pride and prejudice?",
            "16.02.2005.","https://www.imdb.com/title/tt0414387/",
            "drama",null),
        Movie(2,"Pulp Fiction",
            "The lives of two mob hitmen, a boxer, a gangster and his wife, and a pair of diner bandits intertwine in four tales of violence and redemption.",
            "14.10.1994.","https://www.imdb.com/title/tt0110912/",
            "crime",null),
        Movie(3,"The Lord of the Rings",
            "A meek Hobbit from the Shire and eight companions set out on a journey to destroy the powerful One Ring and save Middle-earth from the Dark Lord Sauron.",
            "10.12.2001","https://www.imdb.com/title/tt0120737/",
            "fantasy",null),
        Movie(4,"Serenity",
            "The crew of the ship Serenity try to evade an assassin sent to recapture one of their members who is telepathic.",
            "30.09.2005","https://www.imdb.com/title/tt0379786/",
            "scifi",null),
        Movie(5,"Shaun of the Dead",
            "A man's uneventful life is disrupted by the zombie apocalypse.",
            "09.04.2004","https://www.imdb.com/title/tt0365748/",
            "comedy",null),
        Movie(6,"Watchmen",
            "In 1985 where former superheroes exist, the murder of a colleague sends active vigilante Rorschach into his own sprawling investigation, uncovering something that could completely change the course of history as we know it.",
            "23.02.2009.","https://www.imdb.com/title/tt0409459/",
            "action",null)
    )
}
fun recentMovies(): List<Movie> {
    return listOf(
        Movie(1,"The Contractor",
        "A discharged U.S. Special Forces sergeant, James Harper, risks everything for his family when he joins a private contracting organization.",
        "01.04.2022.","https://www.imdb.com/title/tt10323676/",
        "thriller",null),
        Movie(2,"Nobody",
        "A bystander who intervenes to help a woman being harassed by a group of men becomes the target of a vengeful drug lord.",
        "04.06.2021","https://www.imdb.com/title/tt7888964/",
        "crime",null),
        Movie(3,"Black Widow",
        "A film about Natasha Romanoff in her quests between the films Civil War and Infinity War.",
        "07.04.2021.","https://www.imdb.com/title/tt3480822/",
        "adventure",null),
        Movie(4,"Godzilla vs. Kong ",
        "The epic next chapter in the cinematic Monsterverse pits two of the greatest icons in motion picture history against one another - the fearsome Godzilla and the mighty Kong - with humanity caught in the balance.",
        "01.04.2021","https://www.imdb.com/title/tt5034838",
        "scifi",null),
        Movie(5,"Top Gun: Maverick",
        "After more than thirty years of service as one of the Navy's top aviators, Pete Mitchell is where he belongs, pushing the envelope as a courageous test pilot and dodging the advancement in rank that would ground him.",
        "09.07.2021","https://www.imdb.com/title/tt1745960/",
        "drama",null),
        Movie(6,"Luca",
        "On the Italian Riviera, an unlikely but strong friendship grows between a human being and a sea monster disguised as a human.",
        "18.06.2021.","https://www.imdb.com/title/tt12801262/",
        "family",null)
    )
}
fun movieActors():Map<String,List<String>>{
    return mapOf<String,List<String>>("Pulp Fiction" to listOf("John Travolta","Samuel L. Jackson","Bruce Willis","Amanda Plummer","Laura Lovelace"),"Pride and prejudice" to listOf("Keira Knightley","Talulah Riley","Rosamund Pike"))
}

fun similarMovies():Map<String,List<String>>{
    return mapOf<String,List<String>>("Pulp Fiction" to listOf("Fight Club","Inception","Se7en"),"Pride and prejudice" to listOf("Jane Eyre","The Notebook","Atonement"))
}
