# Newman
#### Programmatic Postman
- [cli](https://learning.postman.com/docs/collections/using-newman-cli/command-line-integration-with-newman/)
- [node library](https://learning.postman.com/docs/collections/using-newman-cli/installing-running-newman/#using-newman-as-a-nodejs-library)

### 1. Setup Npm Project
- Setup directory where you want to develop your `newman` project
- `cd` into directory
- run `npm init`
- run `npm install newman`
- add `**/node_modules/**` to your `.gitignore`

### 2. Create a Collection in Postman that Creates the Data Desired
- In this example our collection contains 12 POST requests:
1. Create Artist:
```
curl --location 'http://localhost:8089/artist' \
--header 'Content-Type: application/json' \
--data '{
    "id": 0,
	"name": "Artist 0",
    "musicGenre": "ROCK",
    "nationality": "ENG"
}'
```
---

2. Create Album:
```
curl --location 'http://localhost:8089' \
--header 'Content-Type: application/json' \
--data '{
  "id": 0,
  "title": Album 0,
  "issueDate": 2010-01-01
}'
```

3-12. Create Tracks 1-10:
```
curl --location 'http://localhost:8089' \
--header 'Content-Type: application/json' \
--data '{
	"title": "Track 01",
    "issueDate": "2010-01-01",
    "durationInSeconds": 220,
    "trackMediaType": "FLAC",
    "language": "English",
    "albumId": 0,
    "artistId": 0
}'
```
- In Postman, *all values should be stored in variables.*
- However, the values you store in the collection within Postman do not matter.
- We will generate the values programmatically below.
- We care about the variable names and will use all of them in our code.
- Export the collection into the project folder.


### 3. Generate + Run Data Through The Collection Programmatically
- Create a new javascript file (e.g. `seed-data.js`):
```js
    // seed-data.js
    const newman = require('newman')
    const fs = require('node:fs')

    const ITERATION_COUNT = 1000
    const ITERATION_DATA_JSON = './seedIteraionData.json'
    const COLLECTION_JSON = './10 Track Album Creator.postman_collection.json'

    // define helper functions to create data
    function artistName(idx) {
        return `Artist ${idx}`
    }

    function albumTitle(idx) {
        return `Album ${idx}`
    }

    function trackTitle(albumIdx, trackIdx) {
        return `${albumIdx} - Track ${trackIdx}`
    }

    function artistMusicGenre(idx) {
        switch (idx % 5) {
            case 4:
                return 'EDM'
            case 3:
                return 'COUNTRY'
            case 2:
                return 'HIPHOP'
            case 1:
                return 'ROCK'
            case 0:
                return 'FOLK'
        }
    }

    function artistNationality(idx) {
        switch (idx % 3) {
            case 2: 
                return 'MEX'
            case 1:
                return 'BRA'
            case 0:
                return 'USA'
        }
    }

    function issueDate(idx) {
        return `20${idx % 10}-${idx % 12}-${idx % 28}`
    }

    function trackLanguage(idx) {
        switch (idx % 3) {
            case 2:
                return 'Spanish'
            case 1:
                return 'Portugese'
            case 0:
                return 'English'
        }
    }

    function trackDuration(idx) {
        return 60 * (idx %  4) + idx % 13
    }

    function trackMediaType(idx) {
        switch (idx % 2) {
            case 1: 
                return 'FLAC'
            case 0:
                return 'MP3'
        }
    }
    
    // Generate data in a loop.
    // The objects generated define the Postman environment variables that will be used while executing the collection.
    // Each object, will be used for one collection run.
    // For our example, iterationData will be an array of objects of the form:
    const iterationData = Array(1000).map((_, idx) => ({
            baseUrl: 'http://localhost:8089',
            albumId: idx,
            artistId: idx,
            artistName: artistName(idx),
            artistNationality: artistNationality(idx),
            artistMusicGenre: artistMusicGenre(idx),
            albumTitle: albumTitle(idx),
            issueDate: issueDate(idx),
            trackLanguage: trackLanguage(idx),
            trackDurationInSeconds: trackDuration(idx),
            trackMediaType: trackMediaType(idx),
            trackLanguage: trackLanguage(idx),
            trackTitle01: trackTitle(idx, 1),
            trackTitle02: trackTitle(idx, 2),
            trackTitle03: trackTitle(idx, 3),
            trackTitle04: trackTitle(idx, 4),
            trackTitle05: trackTitle(idx, 5),
            trackTitle06: trackTitle(idx, 6),
            trackTitle07: trackTitle(idx, 7),
            trackTitle08: trackTitle(idx, 8),
            trackTitle09: trackTitle(idx, 9),
            trackTitle10: trackTitle(idx, 10)
        })
    )

    // write iterationData json
    fs.writeFile(ITERATION_DATA_JSON, JSON.stringify(iterationData), err => {console.dir("ERR"); console.error(err);})

    // run the collection for each element in interationData
    newman.run({
      collection: require(COLLECTION_JSON),
      reporters: 'cli',
      iterationData: ITERATION_DATA_JSON
    }, function (err) {
      if (err) { throw err }
    })
```

### 4. Run Your JS in Node
- `node seed-data.js`


