package gr.uom.smaggregator;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

import static gr.uom.smaggregator.BuildConfig.TWITTER_ACCESS_SECRET_TOKEN;
import static gr.uom.smaggregator.BuildConfig.TWITTER_ACCESS_TOKEN;
import static gr.uom.smaggregator.BuildConfig.TWITTER_CONSUMER_KEY;
import static gr.uom.smaggregator.BuildConfig.TWITTER_CONSUMER_SECRET_KEY;

public class GetTwitterConfig {
    // -----    Get Auth credentials in an interface and export an instance of it.
    public static Twitter getInstance(){
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(TWITTER_CONSUMER_KEY)
                .setOAuthConsumerSecret(TWITTER_CONSUMER_SECRET_KEY)
                .setOAuthAccessToken(TWITTER_ACCESS_TOKEN)
                .setOAuthAccessTokenSecret(TWITTER_ACCESS_SECRET_TOKEN);
        cb.setTweetModeExtended(true);
        TwitterFactory twitter = new TwitterFactory(cb.build());

        return twitter.getInstance();
    };
}
