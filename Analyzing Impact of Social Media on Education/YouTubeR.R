library(vosonSML)
library(magrittr)
library(dplyr)
library(igraph)
myAPIKey <- "YOUR KEY HERE"

youtubeAuth <- Authenticate("youtube", apiKey= myAPIKey)

# collecting Video ids data science
videoIDs <- GetYoutubeVideoIDs(c(
  "https://www.youtube.com/watch?v=Rs_rAxEsAvI",
  "https://www.youtube.com/watch?v=0bFs6ZiynSU",
  "https://www.youtube.com/watch?v=BZTWXl9QNK8",
  "https://www.youtube.com/watch?v=F_Riqjdh2oM",
  "https://www.youtube.com/watch?v=3N9phq_yZP0"
  ))                           

#Collecting YouTube data
youtubeData <- youtubeAuth %>%
  Collect(videoIDs = videoIDs, writeToFile = TRUE)

class(youtubeData) <- append(class(youtubeData), c("datasource", "youtube"))
actorNetwork <- youtubeData %>% Create("actor") %>% AddText(youtubeData)

actorGraph <- actorNetwork %>% Graph(writeToFile = TRUE)


V(actorGraph)$color <- ifelse(V(actorGraph)$node_type=="video", "red", "grey")
png("youtube_actor.png", width=1000, height=1000)
plot(actorGraph, vertex.label="", vertex.size=2, edge.arrow.size=0.1)
dev.off()

# Creating a subnetwork and only replies to top level comments for a single video

g2<- delete.edges(actorGraph, which(E(actorGraph)$edge_type!="reply-comment"))
g2 <- delete.vertices(g2, which(degree(g2)==0))
V(g2)$color <- "grey"
ind <- tail_of(actorGraph,grep("courses| topics| algorithms| data science",tolower(E(g2)$vosonTxt_comment)))
V(g2)$color[ind] <- "green"
png("youtube_actor_reply.png", width=1000, height=1000)
plot(g2, vertex.label="", vertex.size=4, edge.arrow.size=0.1)
dev.off()

# ACTIVITY NETWORK

activityNetwork <- youtubeData %>% Create("activity") %>% AddText(youtubeData)
activityGraph <- activityNetwork %>% Graph(writeToFile = TRUE)
V(activityGraph)$color <- "grey"
V(activityGraph)$color[which(V(activityGraph)$node_type=="video")] <- "red"
ind <- grep("data science| Python | Algortihms ",tolower(V(activityGraph)$vosonTxt_comment))
V(activityGraph)$color[ind] <- "blue"
png("youtube_activity.png", width=600, height=600)
plot(activityGraph, vertex.label="", vertex.size=4, edge.arrow.size=0.5)
dev.off()

# Sentiment Analysis
library(syuzhet)
comments <- iconv(youtubeData$Comment, to= 'utf-8')
s <- get_nrc_sentiment(comments)
s$neutral <- ifelse(s$negative+s$positive == 0, 1, 0)

#creating a bar plot
barplot(100*colSums(s)/sum(s),
        las = 2,
        col = rainbow(10),
        ylab = 'Percentage',
        main = 'Sentiment Score for YouTube comments')


