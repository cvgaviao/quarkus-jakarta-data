package com.cvgaviao.quarkus.jakartadata;

import static jakarta.data.repository.By.ID;

import java.util.List;
import java.util.Optional;

import io.smallrye.mutiny.Uni;
import jakarta.data.exceptions.OptimisticLockingFailureException;
import jakarta.data.page.Page;
import jakarta.data.page.PageRequest;
import jakarta.data.repository.By;
import jakarta.data.repository.Delete;
import jakarta.data.repository.Find;
import jakarta.data.repository.Save;

public interface ReactiveBasicRepository<T, K> extends ReactiveDataRepository<T, K> {

	/**
	 * Deletes a given entity. Deletion is performed by matching the Id, and if the
	 * entity is versioned (for example, with {@code jakarta.persistence.Version}),
	 * then also the version. Other properties of the entity do not need to match.
	 *
	 * @param entity must not be {@code null}.
	 * @throws OptimisticLockingFailureException if the entity is not found in the
	 *                                           database for deletion or has a
	 *                                           version for optimistic locking that
	 *                                           is inconsistent with the version in
	 *                                           the database.
	 * @throws NullPointerException              when the entity is null
	 */
	@Delete
	Uni<Void> delete(T entity);

	/**
	 * Deletes the given entities. Deletion of each entity is performed by matching
	 * the unique identifier, and if the entity is versioned (for example, with
	 * {@code jakarta.persistence.Version}), then also the version. Other properties
	 * of the entity do not need to match.
	 *
	 * @param entities Must not be {@code null}. Must not contain {@code null}
	 *                 elements.
	 * @throws OptimisticLockingFailureException If an entity is not found in the
	 *                                           database for deletion or has a
	 *                                           version for optimistic locking that
	 *                                           is inconsistent with the version in
	 *                                           the database.
	 * @throws NullPointerException              If the iterable is {@code null} or
	 *                                           contains {@code null} elements.
	 */
	@Delete
	Uni<Void> deleteAll(List<? extends T> entities);

	/**
	 * Deletes the entity with the given Id.
	 * <p>
	 * If the entity is not found in the persistence store it is silently ignored.
	 *
	 * @param id must not be {@code null}.
	 * @throws NullPointerException when the Id is {@code null}.
	 */
//	@Delete
//	Uni<Void> deleteById(@By(ID) K id);

	/**
	 * Retrieves all persistent entities of the specified type from the database.
	 *
	 * @return a stream of all entities; will never be {@code null}.
	 * @throws UnsupportedOperationException for Key-Value and Wide-Column databases
	 *                                       that are not capable of the
	 *                                       {@code findAll} operation.
	 */
//	@Find
//	Uni<Stream<T>> findAll();

	/**
	 * Returns a {@link Page} of entities according to the page request that is
	 * provided as the {@link PageRequest} parameter.
	 *
	 * @param pageRequest the request for a paginated result; must not be
	 *                    {@code null}.
	 * @param sortBy      sort criteria that must deterministically order the
	 *                    results; must not be {@code null}.
	 * @return a page of entities; will never be {@code null}.
	 * @throws NullPointerException          when {@code pageRequest} or
	 *                                       {@code sortBy} is {@code null}.
	 * @throws UnsupportedOperationException for Key-Value and Wide-Column databases
	 *                                       when the
	 *                                       {@link PageRequest.Mode#CURSOR_NEXT} or
	 *                                       {@link PageRequest.Mode#CURSOR_PREVIOUS}
	 *                                       pagination mode is selected.
	 * @see PageRequest.Mode
	 */
	@Find
//	Uni<Page<T>> findAll(PageRequest pageRequest, Order<T> sortBy);
	Uni<Page<T>> findAll(PageRequest pageRequest);

	/**
	 * Retrieves an entity by its Id.
	 *
	 * @param id must not be {@code null}.
	 * @return the entity with the given Id or {@link Optional#empty()} if none is
	 *         found.
	 * @throws NullPointerException when the Id is {@code null}.
	 */
	@Find
	Uni<T> findById(@By(ID) K id);

	/**
	 * Saves a given entity to the database. If the entity has an Id or key that
	 * exists in the database, the method will update the existing record.
	 * Otherwise, it will insert a new record.
	 *
	 * <p>
	 * If the entity has a non-null Id, the method will attempt to update the
	 * existing record in the database. If the entity does not exist in the database
	 * or has a null Id, then this method will insert a new record into the
	 * database.
	 * </p>
	 *
	 * <p>
	 * The entity instance that is returned as a result value of this method must be
	 * updated with all automatically generated values and incremented values that
	 * changed due to the save. After invoking this method, do not continue to use
	 * the entity value that is supplied as a parameter. This method makes no
	 * guarantees about the state of the entity value that is supplied as a
	 * parameter.
	 * </p>
	 *
	 * <p>
	 * If the entity uses optimistic locking and the version differs from the
	 * version in the database, an {@link OptimisticLockingFailureException} will be
	 * thrown.
	 * </p>
	 *
	 * @param entity The entity to be saved. Must not be {@code null}.
	 * @param <S>    Type of the entity to save.
	 * @return The saved entity; never {@code null}.
	 * @throws OptimisticLockingFailureException If the entity uses optimistic
	 *                                           locking and the version in the
	 *                                           database differs from the version
	 *                                           in the entity.
	 * @throws NullPointerException              If the provided entity is
	 *                                           {@code null}.
	 */
	@Save
	<S extends T> Uni<S> save(S entity);

	/**
	 * Saves all given entities to the database. If an entity has a non-null Id that
	 * exists in the database, the method will update the existing record.
	 * Otherwise, it will insert a new record.
	 *
	 * <p>
	 * If an entity has a non-null Id, this method will attempt to update the
	 * existing record in the database. If an entity does not exist in the database
	 * or has a null Id, then this method inserts a new record into the database.
	 * </p>
	 *
	 * <p>
	 * The entity instances that are returned as a result of this method must be
	 * updated with all automatically generated values and incremented values that
	 * changed due to the save. After invoking this method, do not continue to use
	 * the entity values that are supplied in the parameter. This method makes no
	 * guarantees about the state of the entity values that are supplied in the
	 * parameter.
	 * </p>
	 *
	 * @param entities An iterable of entities.
	 * @param <S>      Type of entity to save.
	 * @return The saved entities; will never be {@code null}.
	 * @throws OptimisticLockingFailureException If an entity has a version for
	 *                                           optimistic locking that differs
	 *                                           from the version in the database.
	 * @throws NullPointerException              If either the iterable is null or
	 *                                           any element is null.
	 */
//	@Save
//	<S extends T> Uni<List<S>> saveAll(List<S> entities);

}
