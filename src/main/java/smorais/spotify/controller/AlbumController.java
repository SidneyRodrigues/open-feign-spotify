package smorais.spotify.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import smorais.spotify.client.AlbumSpotifyClient;
import smorais.spotify.client.Album;
import smorais.spotify.client.AuthSpotifyClient;
import smorais.spotify.client.LoginRequest;

import java.util.List;

@RestController
@RequestMapping("/spotify/api")
public class AlbumController {

    private final AuthSpotifyClient authSpotifyClient;
    private final AlbumSpotifyClient albumSpotifyClient;

    public AlbumController(AuthSpotifyClient authSpotifyClient,
                           AlbumSpotifyClient albumSpotifyClient) {
        this.authSpotifyClient = authSpotifyClient;
        this.albumSpotifyClient = albumSpotifyClient;
    }

    @GetMapping("/albums")
    public ResponseEntity<List<Album>> helloWorld() {

        var request = new LoginRequest(
                "client_credentials",
                "4de2170fd88d4012bfae7f3099deb50e",
                "bc626f7a1ca34beeafbb23e23559d298"
        );
        var token = authSpotifyClient.login(request).getAccessToken();

        var response = albumSpotifyClient.getReleases("Bearer " + token);


        return ResponseEntity.ok(response.getAlbums().getItems());
    }
}