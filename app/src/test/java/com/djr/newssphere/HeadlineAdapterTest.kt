package com.djr.newssphere

import android.view.ViewGroup
import com.djr.newssphere.data.model.Headline
import com.djr.newssphere.ui.adapters.HeadlineAdapter
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class HeadlineAdapterTest {

    private lateinit var headlineAdapter: HeadlineAdapter

    @Mock
    private lateinit var mockHeadline: Headline

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        headlineAdapter = HeadlineAdapter { /* onItemClick implementation */ }
    }

    @Test
    fun onCreateViewHolder_validParent_returnsHeadlineViewHolder() {
        val parent: ViewGroup = mock(ViewGroup::class.java)
        val viewHolder = headlineAdapter.onCreateViewHolder(parent, 0)
        assertEquals(HeadlineAdapter.HeadlineViewHolder::class.java, viewHolder.javaClass)
    }

    @Test
    fun onBindViewHolder_bindsDataToViewHolder() {
        val mockViewHolder: HeadlineAdapter.HeadlineViewHolder = mock(HeadlineAdapter.HeadlineViewHolder::class.java)
        val position = 0

        headlineAdapter.onBindViewHolder(mockViewHolder, position)

        // Verify that the bind() function is called with the correct headline
        verify(mockViewHolder).bind(mockHeadline)

        // Verify that the onItemClick listener is set correctly
        // Assuming onItemClick implementation verifies if the click event is received correctly
    }

    @Test
    fun areItemsTheSame_sameTitles_returnsTrue() {
        val oldItem: Headline = mock(Headline::class.java)
        val newItem: Headline = mock(Headline::class.java)
        `when`(oldItem.title).thenReturn("Title")
        `when`(newItem.title).thenReturn("Title")

        val result = HeadlineAdapter.HeadlineDiffCallback().areItemsTheSame(oldItem, newItem)

        assertEquals(true, result)
    }

    @Test
    fun areItemsTheSame_differentTitles_returnsFalse() {
        val oldItem: Headline = mock(Headline::class.java)
        val newItem: Headline = mock(Headline::class.java)
        `when`(oldItem.title).thenReturn("Title1")
        `when`(newItem.title).thenReturn("Title2")

        val result = HeadlineAdapter.HeadlineDiffCallback().areItemsTheSame(oldItem, newItem)

        assertEquals(false, result)
    }

    @Test
    fun areContentsTheSame_sameHeadline_returnsTrue() {
        val oldItem: Headline = mock(Headline::class.java)
        val newItem: Headline = oldItem

        val result = HeadlineAdapter.HeadlineDiffCallback().areContentsTheSame(oldItem, newItem)

        assertEquals(true, result)
    }

    @Test
    fun areContentsTheSame_differentHeadlines_returnsFalse() {
        val oldItem: Headline = mock(Headline::class.java)
        val newItem: Headline = mock(Headline::class.java)

        val result = HeadlineAdapter.HeadlineDiffCallback().areContentsTheSame(oldItem, newItem)

        assertEquals(false, result)
    }
}
