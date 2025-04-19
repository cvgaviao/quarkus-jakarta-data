package com.cvgaviao.quarkus.jakartadata;

import java.util.List;

import io.smallrye.mutiny.Uni;
import jakarta.data.exceptions.EntityExistsException;
import jakarta.data.exceptions.OptimisticLockingFailureException;
import jakarta.data.repository.Insert;
import jakarta.data.repository.Update;

public interface ReactiveCrudRepository<T, K> extends ReactiveBasicRepository<T, K> {
	/**
	 * <p>
	 * Inserts an entity into the database. If an entity of this type with the same
	 * unique identifier already exists in the database and the database supports
	 * ACID transactions, then this method raises {@link EntityExistsException}. In
	 * databases that follow the BASE model or use an append model to write data,
	 * this exception is not thrown.
	 * </p>
	 *
	 * <p>
	 * The entity instance returned as a result of this method must include all
	 * values that were written to the database, including all automatically
	 * generated values and incremented values that changed due to the insert. After
	 * invoking this method, do not continue to use the instance that is supplied as
	 * a parameter. This method makes no guarantees about the state of the instance
	 * that is supplied as a parameter.
	 * </p>
	 *
	 * @param entity the entity to insert. Must not be {@code null}.
	 * @param <S>    Type of the entity to insert.
	 * @return the inserted entity, which may or may not be a different instance
	 *         depending on whether the insert caused values to be generated or
	 *         automatically incremented.
	 * @throws EntityExistsException if the entity is already present in the
	 *                               database (in ACID-supported databases).
	 * @throws NullPointerException  if the entity is null.
	 */
	@Insert
	<S extends T> Uni<S> insert(S entity);

	/**
	 * <p>
	 * Inserts multiple entities into the database. If any entity of this type with
	 * the same unique identifier as any of the given entities already exists in the
	 * database and the database supports ACID transactions, then this method raises
	 * {@link EntityExistsException}. In databases that follow the BASE model or use
	 * an append model to write data, this exception is not thrown.
	 * </p>
	 *
	 * <p>
	 * The entities within the returned {@link Iterable} must include all values
	 * that were written to the database, including all automatically generated
	 * values and incremented values that changed due to the insert. After invoking
	 * this method, do not continue to use the entity instances that are supplied in
	 * the parameter. This method makes no guarantees about the state of the entity
	 * instances that are supplied in the parameter. The position of entities within
	 * the {@code Iterable} return value must correspond to the position of entities
	 * in the parameter based on the unique identifier of the entity.
	 * </p>
	 *
	 * @param entities entities to insert.
	 * @param <S>      Type of the entities to insert.
	 * @return an iterable containing the inserted entities, which may or may not be
	 *         different instances depending on whether the insert caused values to
	 *         be generated or automatically incremented.
	 * @throws EntityExistsException if any of the entities are already present in
	 *                               the database (in ACID-supported databases).
	 * @throws NullPointerException  if the iterable is null or any element is null.
	 */
	@Insert
	<S extends T> Uni<List<S>> insertAll(List<S> entities);

	/**
	 * <p>
	 * Modifies an entity that already exists in the database.
	 * </p>
	 *
	 * <p>
	 * For an update to be made, a matching entity with the same unique identifier
	 * must be present in the database. In databases that use an append model to
	 * write data or follow the BASE model, this method behaves the same as the
	 * {@link #insert} method.
	 * </p>
	 *
	 * <p>
	 * If the entity is versioned (for example, with
	 * {@code jakarta.persistence.Version} or by another convention from the entity
	 * model such as having an attribute named {@code version}), then the version
	 * must also match. The version is automatically incremented when making the
	 * update.
	 * </p>
	 *
	 * @param entity the entity to update. Must not be {@code null}.
	 * @param <S>    Type of the entity to update.
	 * @return an updated entity instance including all automatically generated
	 *         values, updated versions, and incremented values which changed as a
	 *         result of the update.
	 * @throws OptimisticLockingFailureException the entity is not found in the
	 *                                           database or has a version that
	 *                                           differs from the version in the
	 *                                           database.
	 * @throws NullPointerException              if the entity is null.
	 */
	@Update
	<S extends T> Uni<S> update(S entity);

	/**
	 * <p>
	 * Modifies entities that already exist in the database.
	 * </p>
	 *
	 * <p>
	 * For an update to be made to an entity, a matching entity with the same unique
	 * identifier must be present in the database. In databases that use an append
	 * model to write data or follow the BASE model, this method behaves the same as
	 * the {@link #insertAll} method.
	 * </p>
	 *
	 * <p>
	 * If the entity is versioned (for example, with
	 * {@code jakarta.persistence.Version} or by another convention from the entity
	 * model such as having an attribute named {@code version}), then the version
	 * must also match. The version is automatically incremented when making the
	 * update.
	 * </p>
	 *
	 * @param entities entities to update.
	 * @param <S>      Type of the entities to update.
	 * @return updated entity instances, in the same order as the supplied entities,
	 *         and including all automatically generated values, updated versions,
	 *         and incremented values which changed as a result of the update.
	 * @throws OptimisticLockingFailureException If any of the supplied entities is
	 *                                           not found in the database or has a
	 *                                           version that differs from the
	 *                                           version in the database.
	 * @throws NullPointerException              if either the supplied
	 *                                           {@code Iterable} is null or any
	 *                                           element is null.
	 */
	@Update
	<S extends T> Uni<List<S>> updateAll(List<S> entities);
}
