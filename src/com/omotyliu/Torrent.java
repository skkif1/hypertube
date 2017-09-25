package com.omotyliu;

import bt.Bt;
import bt.data.Storage;
import bt.data.file.FileSystemStorage;
import bt.dht.DHTConfig;
import bt.dht.DHTModule;
import bt.runtime.BtClient;
import bt.runtime.Config;
import com.google.inject.Module;
import com.turn.ttorrent.client.Client;
import com.turn.ttorrent.client.SharedTorrent;
import com.turn.ttorrent.tracker.TrackedTorrent;
import com.turn.ttorrent.tracker.Tracker;

import javax.sound.midi.Track;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.file.Path;
import java.security.NoSuchAlgorithmException;
import java.util.Observable;
import java.util.Observer;

public class Torrent {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {

        Client client = new Client(
                // This is the interface the client will listen on (you might need something
                // else than localhost here).
                InetAddress.getLocalHost(),

                // Load the torrent from the torrent file and use the given
                // output directory. Partials downloads are automatically recovered.
                SharedTorrent.fromFile(
                        new File("/nfs/2016/o/omotyliu/Library/Containers/MAMP/apache2/htdocs/webapp/torrent.torrent"),
                        new File("/nfs/2016/o/omotyliu/Downloads")));

// You can optionally set download/upload rate limits
// in kB/second. Setting a limit to 0.0 disables rate
// limits.
        client.setMaxDownloadRate(50.0);
        client.setMaxUploadRate(50.0);

// At this point, can you either call download() to download the torrent and
// stop immediately after...
        client.download();

// Or call client.share(...) with a seed time in seconds:
// client.share(3600);
// Which would seed the torrent for an hour after the download is complete.

// Downloading and seeding is done in background threads.
// To wait for this process to finish, call:

        client.addObserver(new Observer() {

            @Override
            public void update(Observable observable, Object data) {
                Client client = (Client) observable;
                float progress = client.getTorrent().getCompletion();
                System.out.println(progress);
            }
        });

        client.waitForCompletion();

// At any time you can call client.stop() to interrupt the download.
    }
}
