package com.example.android.hilt.contentprovider

import android.content.*
import android.database.Cursor
import android.net.Uri
import com.example.android.hilt.data.LogDao
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent

/**
 * A Content Provider to expose logs information outside the application process.
 */
class LogsContentProvider : ContentProvider() {

    companion object {
        // Authority of the Content Provider
        private const val CONTENT_AUTHORITY = "com.example.android.hilt.provider"

        // Identifier for the table "logs" associated with the Base URI of the Content Provider
        private const val PATH_LOGS = "logs"

        // URI Matcher codes for identifying the URI of Log and its descendant relationships
        private const val LOGS = 1 // for all logs from the table "logs"
        private const val LOG_ID = 2 // for particular log by id from the table "logs"

        // URI Matcher for matching the possible URI
        private val matcher: UriMatcher
            get() = UriMatcher(UriMatcher.NO_MATCH).apply {
                // With the empty UriMatcher for root node, add other URIs to match
                // For "content://AUTHORITY/logs" that references the entire "logs" table
                addURI(CONTENT_AUTHORITY, PATH_LOGS, LOGS)
                // For "content://AUTHORITY/logs/#" that references a record in "logs" table
                addURI(CONTENT_AUTHORITY, "$PATH_LOGS/#", LOG_ID)
            }
    }

    @InstallIn(SingletonComponent::class)
    @EntryPoint
    interface LogsContentProviderEntryPoint {
        fun logDao(): LogDao
    }

    /**
     * Method to get [LogDao] binding instance from Hilt via [EntryPointAccessors].
     *
     * @param appContext Application [Context] required to retrieve the [LogDao] binding instance
     * @return Instance of [LogDao]
     */
    private fun getLogDao(appContext: Context): LogDao {
        // Get the entry point defined for this Content Provider
        val entryPoint = EntryPointAccessors.fromApplication(
            appContext,
            LogsContentProviderEntryPoint::class.java
        )
        // Return the LogDao instance from the entry point
        return entryPoint.logDao()
    }

    /**
     * Implement this to initialize your content provider on startup.
     *
     * @return true if the provider was successfully loaded, false otherwise
     */
    override fun onCreate(): Boolean {
        // No helper object needed, hence returning true to indicate
        // that the provider is loaded successfully
        return true
    }

    /**
     * Handle query requests from clients.
     *
     * @param uri The URI to query. This will be the full URI sent by the client;
     * if the client is requesting a specific record, the URI will end in a record number
     * that the implementation should parse and add to a WHERE or HAVING clause, specifying
     * that _id value.
     * @param projection The list of columns to put into the cursor. If
     * `null` all columns are included.
     * @param selection A selection criteria to apply when filtering rows.
     * If `null` then all rows are included.
     * @param selectionArgs You may include ?s in selection, which will be replaced by
     * the values from selectionArgs, in order that they appear in the selection.
     * The values will be bound as Strings.
     * @param sortOrder How the rows in the cursor should be sorted.
     * If `null` then the provider is free to define the sort order.
     * @return a Cursor or `null`.
     */
    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        // Read Application context
        val appContext: Context = context?.applicationContext ?: throw IllegalStateException()
        // Get LogDao instance from Hilt entry point
        val logDao: LogDao = getLogDao(appContext)

        // Match the queried URI to execute the appropriate query
        val cursor: Cursor? = when (matcher.match(uri)) {
            LOGS -> logDao.selectAllLogsCursor()
            LOG_ID -> logDao.selectLogById(ContentUris.parseId(uri))
            else -> throw UnsupportedOperationException("Cannot query unknown URI $uri")
        }

        // Return the query result obtained
        return cursor?.also {
            // Register content resolver to watch the URI for changes and notify its attached listeners
            it.setNotificationUri(appContext.contentResolver, uri)
        }
    }

    /**
     * Implement this to handle requests for the MIME type of the data at the
     * given URI.  The returned MIME type should start with
     * `vnd.android.cursor.item` for a single record,
     * or `vnd.android.cursor.dir/` for multiple items.
     *
     * @param uri the URI to query.
     * @return a MIME type string, or `null` if there is no type.
     */
    override fun getType(uri: Uri): String? {
        throw UnsupportedOperationException("Only read operations allowed")
    }

    /**
     * Implement this to handle requests to insert a new row. As a courtesy,
     * call [notifyChange()][ContentResolver.notifyChange] after inserting.
     *
     * @param uri The content:// URI of the insertion request.
     * @param values A set of column_name/value pairs to add to the database.
     * @return The URI for the newly inserted item.
     */
    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        throw UnsupportedOperationException("Only read operations allowed")
    }

    /**
     * Implement this to handle requests to delete one or more rows. The
     * implementation should apply the selection clause when performing
     * deletion, allowing the operation to affect multiple rows in a directory.
     * As a courtesy, call [notifyChange()][ContentResolver.notifyChange] after deleting.
     *
     * @param uri The full URI to query, including a row ID (if a specific
     * record is requested).
     * @param selection An optional restriction to apply to rows when deleting.
     * @return The number of rows affected.
     * @throws java.sql.SQLException
     */
    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        throw UnsupportedOperationException("Only read operations allowed")
    }

    /**
     * Implement this to handle requests to update one or more rows. The
     * implementation should update all rows matching the selection to set the
     * columns according to the provided values map. As a courtesy, call
     * [notifyChange()][ContentResolver.notifyChange] after updating.
     *
     * @param uri The URI to query. This can potentially have a record ID if
     * this is an update request for a specific record.
     * @param values A set of column_name/value pairs to update in the database.
     * @param selection An optional filter to match rows to update.
     * @return the number of rows affected.
     */
    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        throw UnsupportedOperationException("Only read operations allowed")
    }
}