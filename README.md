# Document-clustering-by-vector-space-model
A unique concept of Document clustering 


### Introduction
    Clustering can be considered the most important unsupervised learning problem; so, as every other problem of this kind, it deals with 
finding a structure in a collection of unlabeled data. A loose definition of clustering could be “the process of organizing objects 
into groups whose members are similar in some way”. A cluster is therefore a collection of objects which are coherent internally, but
clearly dissimilar to the objects belonging to other clusters.


###Classification

Clustering algorithms may be classified as listed below

######Flat clustering (Creates a set of clusters without any explicit structure that would relate clusters to each other; 
It’s also called exclusive clustering)
######Hierarchical clustering (Creates a hierarchy of clusters)
######Hard clustering (Assigns each document/object as a member of exactly one cluster)
######Soft clustering (Distribute the document/object over all clusters)

###Algorithms
#######Agglomerative (Hierarchical clustering)
######K-Means (Flat clustering, Hard clustering)
######EM Algorithm (Flat clustering, Soft clustering)
######Hierarchical Agglomerative Clustering (HAC) and K-Means algorithm have been applied to text clustering in a straightforward way. Typically it usages normalized, TF-IDF-weighted vectors and cosine similarity. Here, I have illustrated the k-means algorithm using a set of points in n-dimensional vector space for text clustering.
