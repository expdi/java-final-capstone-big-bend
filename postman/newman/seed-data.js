const newman = require('newman')
const fs = require('node:fs')

const ITERATION_COUNT = 10
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
    return `202${idx % 10}-07-1${idx % 10}`
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
const iterationData = Array(ITERATION_COUNT).map((_, idx) => {
    idx += 1
    const res = {
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
    }
    return res
})

// write iterationData json
fs.writeFile(ITERATION_DATA_JSON, JSON.stringify(iterationData), err => {console.dir("ERR"); console.error(err);})

// run the collection for each element in interationData
newman.run({
    collection: require(COLLECTION_JSON),
    reporters: 'cli',
    iterationData: ITERATION_DATA_JSON
}, function (err) {
    // if (err) { throw err }
})
