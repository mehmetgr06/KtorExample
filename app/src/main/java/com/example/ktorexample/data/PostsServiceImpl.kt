package com.example.ktorexample.data

import com.example.ktorexample.data.dto.PostRequest
import com.example.ktorexample.data.dto.PostResponse
import io.ktor.client.HttpClient
import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.RedirectResponseException
import io.ktor.client.features.ServerResponseException
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType
import java.lang.Exception

class PostsServiceImpl(private val client: HttpClient) : PostsService {

    override suspend fun getPosts(): List<PostResponse> {
        return try {
            client.get {
                url(HttpRoutes.POSTS)
            }
        } catch (e: RedirectResponseException) {
            // 3xx
            println("Error: ${e.response.status.description}")
            emptyList()
        }
        catch (e: ClientRequestException) {
            // 4xx
            println("Error: ${e.response.status.description}")
            emptyList()
        }
        catch (e: ServerResponseException) {
            // 3xx
            println("Error: ${e.response.status.description}")
            emptyList()
        }
        catch (e: Exception) {
            // 3xx
            println("Error: ${e.message}")
            emptyList()
        }
    }

    override suspend fun createPost(postRequest: PostRequest): PostResponse? {
        return try {
            client.post<PostResponse> {
                url(HttpRoutes.POSTS)
                contentType(ContentType.Application.Json)
                body = postRequest
            }
        } catch (e: RedirectResponseException) {
            // 3xx
            println("Error: ${e.response.status.description}")
            null
        }
        catch (e: ClientRequestException) {
            // 4xx
            println("Error: ${e.response.status.description}")
            null
        }
        catch (e: ServerResponseException) {
            // 3xx
            println("Error: ${e.response.status.description}")
            null
        }
        catch (e: Exception) {
            // 3xx
            println("Error: ${e.message}")
            null
        }
    }


}