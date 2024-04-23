package com.minispotify.gateway.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class GatewayController {

    @Value("${artist.service.url}")
    private String artistServiceUrl;
    @Value("${song.service.url}")
    private String songServiceUrl;
    @Value("${playlist.service.url}")
    private String playlistServiceUrl;
    @Value("${user.service.url}")
    private String userServiceUrl;
    @Value("${authentication.service.url}")
    private String authenticationServiceUrl;

    private final RestTemplate restTemplate;
    private final RedisTemplate<String, String> redisTemplate;

    // Artist Service Endpoints
    @GetMapping("/artists")
    public ResponseEntity<String> getAllArtists(@RequestHeader("Authorization") String token) {
        ResponseEntity<String> verifyResponse = verifyToken(token);
        if (verifyResponse.getStatusCode() != HttpStatus.OK) {
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
        return restTemplate.getForEntity(artistServiceUrl + "/artists", String.class);
    }

    @GetMapping("/artists/{artistId}")
    public ResponseEntity<String> getArtistById(@RequestHeader("Authorization") String token, @RequestParam String artistId) {
        ResponseEntity<String> verifyResponse = verifyToken(token);
        if (verifyResponse.getStatusCode() != HttpStatus.OK) {
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
        return restTemplate.getForEntity(artistServiceUrl + "/artists/" + artistId, String.class);
    }

    @PostMapping("/artists")
    public ResponseEntity<String> createArtist(@RequestHeader("Authorization") String token, @RequestBody String artistDetails) {
        ResponseEntity<String> verifyResponse = verifyToken(token);
        if (verifyResponse.getStatusCode() != HttpStatus.OK) {
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
        return restTemplate.postForEntity(artistServiceUrl + "/artists", new HttpEntity<>(artistDetails), String.class);
    }

    @PutMapping("/artists/{artistId}")
    public ResponseEntity<String> updateArtist(@RequestHeader("Authorization") String token, @RequestParam String artistId, @RequestBody String updatedArtistDetails) {
        ResponseEntity<String> verifyResponse = verifyToken(token);
        if (verifyResponse.getStatusCode() != HttpStatus.OK) {
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
        return restTemplate.exchange(artistServiceUrl + "/artists/" + artistId, HttpMethod.PUT, new HttpEntity<>(updatedArtistDetails), String.class);
    }

    @DeleteMapping("/artists/{artistId}")
    public void deleteArtist(@RequestHeader("Authorization") String token, @RequestParam String artistId) {
        ResponseEntity<String> verifyResponse = verifyToken(token);
        if (verifyResponse.getStatusCode() != HttpStatus.OK) {
            return;
        }
        restTemplate.delete(artistServiceUrl + "/artists/" + artistId);
    }

    // Song Service Endpoints
    @GetMapping("/songs")
    public ResponseEntity<String> getAllSongs(@RequestHeader("Authorization") String token) {
        ResponseEntity<String> verifyResponse = verifyToken(token);
        if (verifyResponse.getStatusCode() != HttpStatus.OK) {
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
        return restTemplate.getForEntity(songServiceUrl + "/songs", String.class);
    }

    @GetMapping("/songs/{songId}")
    public ResponseEntity<String> getSongById(@RequestHeader("Authorization") String token, @RequestParam String songId) {
        ResponseEntity<String> verifyResponse = verifyToken(token);
        if (verifyResponse.getStatusCode() != HttpStatus.OK) {
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
        return restTemplate.getForEntity(songServiceUrl + "/songs/" + songId, String.class);
    }

    @PostMapping("/songs")
    public ResponseEntity<String> createSong(@RequestHeader("Authorization") String token, @RequestBody String songDetails) {
        ResponseEntity<String> verifyResponse = verifyToken(token);
        if (verifyResponse.getStatusCode() != HttpStatus.OK) {
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
        return restTemplate.postForEntity(songServiceUrl + "/songs", new HttpEntity<>(songDetails), String.class);
    }

    @PutMapping("/songs/{songId}")
    public ResponseEntity<String> updateSong(@RequestHeader("Authorization") String token, @RequestParam String songId, @RequestBody String updatedSongDetails) {
        ResponseEntity<String> verifyResponse = verifyToken(token);
        if (verifyResponse.getStatusCode() != HttpStatus.OK) {
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
        return restTemplate.exchange(songServiceUrl + "/songs/" + songId, HttpMethod.PUT, new HttpEntity<>(updatedSongDetails), String.class);
    }

    @DeleteMapping("/songs/{songId}")
    public void deleteSong(@RequestHeader("Authorization") String token, @RequestParam String songId) {
        ResponseEntity<String> verifyResponse = verifyToken(token);
        if (verifyResponse.getStatusCode() != HttpStatus.OK) {
            return;
        }
        restTemplate.delete(songServiceUrl + "/songs/" + songId);
    }

    // Playlist Service Endpoints
    @GetMapping("/playlists")
    public ResponseEntity<String> getAllPlaylists(@RequestHeader("Authorization") String token) {
        ResponseEntity<String> verifyResponse = verifyToken(token);
        if (verifyResponse.getStatusCode() != HttpStatus.OK) {
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
        return restTemplate.getForEntity(playlistServiceUrl + "/playlists", String.class);
    }

    @GetMapping("/playlists/{playlistId}")
    public ResponseEntity<String> getPlaylistById(@RequestHeader("Authorization") String token, @RequestParam String playlistId) {
        ResponseEntity<String> verifyResponse = verifyToken(token);
        if (verifyResponse.getStatusCode() != HttpStatus.OK) {
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
        return restTemplate.getForEntity(playlistServiceUrl + "/playlists/" + playlistId, String.class);
    }

    @PostMapping("/playlists")
    public ResponseEntity<String> createPlaylist(@RequestHeader("Authorization") String token, @RequestBody String playlistDetails) {
        ResponseEntity<String> verifyResponse = verifyToken(token);
        if (verifyResponse.getStatusCode() != HttpStatus.OK) {
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
        return restTemplate.postForEntity(playlistServiceUrl + "/playlists", new HttpEntity<>(playlistDetails), String.class);
    }

    @PutMapping("/playlists/{playlistId}")
    public ResponseEntity<String> updatePlaylist(@RequestHeader("Authorization") String token, @RequestParam String playlistId, @RequestBody String updatedPlaylistDetails) {
        ResponseEntity<String> verifyResponse = verifyToken(token);
        if (verifyResponse.getStatusCode() != HttpStatus.OK) {
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
        return restTemplate.exchange(playlistServiceUrl + "/playlists/" + playlistId, HttpMethod.PUT, new HttpEntity<>(updatedPlaylistDetails), String.class);
    }

    @DeleteMapping("/playlists/{playlistId}")
    public void deletePlaylist(@RequestHeader("Authorization") String token, @RequestParam String playlistId) {
        ResponseEntity<String> verifyResponse = verifyToken(token);
        if (verifyResponse.getStatusCode() != HttpStatus.OK) {
            return;
        }
        restTemplate.delete(playlistServiceUrl + "/playlists/" + playlistId);
    }

    // User Service Endpoints
    @GetMapping("/users")
    public ResponseEntity<String> getAllUsers(@RequestHeader("Authorization") String token) {
        ResponseEntity<String> verifyResponse = verifyToken(token);
        if (verifyResponse.getStatusCode() != HttpStatus.OK) {
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
        return restTemplate.getForEntity(userServiceUrl + "/users", String.class);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<String> getUserById(@RequestHeader("Authorization") String token, @RequestParam String userId) {
        ResponseEntity<String> verifyResponse = verifyToken(token);
        if (verifyResponse.getStatusCode() != HttpStatus.OK) {
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
        return restTemplate.getForEntity(userServiceUrl + "/users/" + userId, String.class);
    }

    @PostMapping("/users")
    public ResponseEntity<String> createUser(@RequestHeader("Authorization") String token, @RequestBody String userDetails) {
        ResponseEntity<String> verifyResponse = verifyToken(token);
        if (verifyResponse.getStatusCode() != HttpStatus.OK) {
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
        return restTemplate.postForEntity(userServiceUrl + "/users", new HttpEntity<>(userDetails), String.class);
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<String> updateUser(@RequestHeader("Authorization") String token, @RequestParam String userId, @RequestBody String updatedUserDetails) {
        ResponseEntity<String> verifyResponse = verifyToken(token);
        if (verifyResponse.getStatusCode() != HttpStatus.OK) {
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
        return restTemplate.exchange(userServiceUrl + "/users/" + userId, HttpMethod.PUT, new HttpEntity<>(updatedUserDetails), String.class);
    }

    @DeleteMapping("/users/{userId}")
    public void deleteUser(@RequestHeader("Authorization") String token, @RequestParam String userId) {
        ResponseEntity<String> verifyResponse = verifyToken(token);
        if (verifyResponse.getStatusCode() != HttpStatus.OK) {
            return;
        }
        restTemplate.delete(userServiceUrl + "/users/" + userId);
    }

    // Functional endpoints
    @GetMapping("/users/{userId}/playlists")
    public ResponseEntity<String> getUserPlaylists(@RequestHeader("Authorization") String token, @RequestParam String userId) {
        ResponseEntity<String> verifyResponse = verifyToken(token);
        if (verifyResponse.getStatusCode() != HttpStatus.OK) {
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
        return restTemplate.getForEntity(userServiceUrl + "/users/" + userId + "/playlists", String.class);
    }

    @PostMapping("/users/{userId}/playlists/{playlistId}")
    public ResponseEntity<String> addUserPlaylist(@RequestHeader("Authorization") String token, @RequestParam String userId, @RequestParam String playlistId) {
        ResponseEntity<String> verifyResponse = verifyToken(token);
        if (verifyResponse.getStatusCode() != HttpStatus.OK) {
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
        return restTemplate.postForEntity(userServiceUrl + "/users/" + userId + "/playlists/" + playlistId, null, String.class);
    }

    @DeleteMapping("/users/{userId}/playlists/{playlistId}")
    public void removeUserPlaylist(@RequestHeader("Authorization") String token, @RequestParam String userId, @RequestParam String playlistId) {
        ResponseEntity<String> verifyResponse = verifyToken(token);
        if (verifyResponse.getStatusCode() != HttpStatus.OK) {
            return;
        }
        restTemplate.delete(userServiceUrl + "/users/" + userId + "/playlists/" + playlistId);
    }

    @GetMapping("/users/{userId}/songs")
    public ResponseEntity<String> getUserSongs(@RequestHeader("Authorization") String token, @RequestParam String userId) {
        ResponseEntity<String> verifyResponse = verifyToken(token);
        if (verifyResponse.getStatusCode() != HttpStatus.OK) {
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
        return restTemplate.getForEntity(userServiceUrl + "/users/" + userId + "/songs", String.class);
    }

    @PostMapping("/users/{userId}/songs/{songId}")
    public ResponseEntity<String> addUserSong(@RequestHeader("Authorization") String token, @RequestParam String userId, @RequestParam String songId) {
        ResponseEntity<String> verifyResponse = verifyToken(token);
        if (verifyResponse.getStatusCode() != HttpStatus.OK) {
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
        return restTemplate.postForEntity(userServiceUrl + "/users/" + userId + "/songs/" + songId, null, String.class);
    }

    @DeleteMapping("/users/{userId}/songs/{songId}")
    public void removeUserSong(@RequestHeader("Authorization") String token, @RequestParam String userId, @RequestParam String songId) {
        ResponseEntity<String> verifyResponse = verifyToken(token);
        if (verifyResponse.getStatusCode() != HttpStatus.OK) {
            return;
        }
        restTemplate.delete(userServiceUrl + "/users/" + userId + "/songs/" + songId);
    }

    @GetMapping("/users/{userId}/artists")
    public ResponseEntity<String> getUserArtists(@RequestHeader("Authorization") String token, @RequestParam String userId) {
        ResponseEntity<String> verifyResponse = verifyToken(token);
        if (verifyResponse.getStatusCode() != HttpStatus.OK) {
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
        return restTemplate.getForEntity(userServiceUrl + "/users/" + userId + "/artists", String.class);
    }

    @PostMapping("/users/{userId}/artists/{artistId}")
    public ResponseEntity<String> addUserArtist(@RequestHeader("Authorization") String token, @RequestParam String userId, @RequestParam String artistId) {
        ResponseEntity<String> verifyResponse = verifyToken(token);
        if (verifyResponse.getStatusCode() != HttpStatus.OK) {
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
        return restTemplate.postForEntity(userServiceUrl + "/users/" + userId + "/artists/" + artistId, null, String.class);
    }

    @DeleteMapping("/users/{userId}/artists/{artistId}")
    public void removeUserArtist(@RequestHeader("Authorization") String token, @RequestParam String userId, @RequestParam String artistId) {
        ResponseEntity<String> verifyResponse = verifyToken(token);
        if (verifyResponse.getStatusCode() != HttpStatus.OK) {
            return;
        }
        restTemplate.delete(userServiceUrl + "/users/" + userId + "/artists/" + artistId);
    }

    @PostMapping("/users/{userId}/playlists/{playlistId}/songs/{songId}")
    public ResponseEntity<String> addSongToPlaylist(@RequestHeader("Authorization") String token, @RequestParam String userId, @RequestParam String playlistId, @RequestParam String songId) {
        ResponseEntity<String> verifyResponse = verifyToken(token);
        if (verifyResponse.getStatusCode() != HttpStatus.OK) {
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
        return restTemplate.postForEntity(userServiceUrl + "/users/" + userId + "/playlists/" + playlistId + "/songs/" + songId, null, String.class);
    }

    @DeleteMapping("/users/{userId}/playlists/{playlistId}/songs/{songId}")
    public void removeSongFromPlaylist(@RequestHeader("Authorization") String token, @RequestParam String userId, @RequestParam String playlistId, @RequestParam String songId) {
        ResponseEntity<String> verifyResponse = verifyToken(token);
        if (verifyResponse.getStatusCode() != HttpStatus.OK) {
            return;
        }
        restTemplate.delete(userServiceUrl + "/users/" + userId + "/playlists/" + playlistId + "/songs/" + songId);
    }

    @GetMapping("/users/{userId}/playlists/{playlistId}/songs")
    public ResponseEntity<String> getPlaylistSongs(@RequestHeader("Authorization") String token, @RequestParam String userId, @RequestParam String playlistId) {
        ResponseEntity<String> verifyResponse = verifyToken(token);
        if (verifyResponse.getStatusCode() != HttpStatus.OK) {
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
        return restTemplate.getForEntity(userServiceUrl + "/users/" + userId + "/playlists/" + playlistId + "/songs", String.class);
    }

    // Authentication Service Endpoints
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody String loginDetails) {
        return restTemplate.postForEntity(authenticationServiceUrl + "/login", new HttpEntity<>(loginDetails), String.class);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody String registrationDetails) {
        return restTemplate.postForEntity(authenticationServiceUrl + "/register", new HttpEntity<>(registrationDetails), String.class);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody String logoutDetails) {
        return restTemplate.postForEntity(authenticationServiceUrl + "/logout", new HttpEntity<>(logoutDetails), String.class);
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verifyToken(String token) {
        //search in redis first if there isnt a token unauthorized

        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        String value = ops.get(token);
        if (Objects.isNull(value)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(value, HttpStatus.OK);
    }
}